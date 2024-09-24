const handleFileSelect = (event) => {
    const file = event.target.files[0];
    const imagePreview = document.getElementById('imagePreview');

    if (file) {
        const reader = new FileReader();
        reader.onload = (e) => {
            imagePreview.innerHTML = `<img src="${e.target.result}" alt="Preview" style="width: 170px; height: 170px;">`;
        };
        reader.readAsDataURL(file);
    } else {
        imagePreview.innerHTML = ''; // 파일이 선택되지 않았을 때
    }
}

let petSpeciesId;

// 모달이 열릴 때 전체 견종 리스트를 비동기로 불러오기
$('#breedModal').on('shown.bs.modal', () => {
    loadBreedList(''); // 전체 견종 리스트 로드
});

// 검색 버튼 클릭 시 호출되는 함수
const searchBreed = () => {
    const query = document.getElementById('searchBreed').value;
    loadBreedList(query); // 검색어를 이용한 견종 리스트 로드
}

// 견종 리스트 로드 함수 (검색어가 있을 경우 해당 검색어를 이용)
const loadBreedList = (query) => {
    axios.get('/v1/customers/pets/species', {
        params: {query: query}  // 검색어를 서버로 전송
    })
        .then((response) => {
            console.log(response);
            const breedList = document.getElementById('breedList');
            const noResults = document.getElementById('noResults');
            breedList.innerHTML = ''; // 기존 리스트 초기화
            noResults.classList.add('d-none'); // "검색 결과 없음" 메시지 숨기기

            if (response.data.length === 0) {
                noResults.classList.remove('d-none'); // 검색 결과 없음 메시지 표시
            } else {
                response.data.forEach((breed) => {
                    const li = document.createElement('li');
                    li.textContent = breed.species;
                    li.dataset.breedId = breed.id; // 선택 시 ID를 받아오기 위해 저장
                    li.classList.add('list-group-item');
                    li.onclick = () => {
                        // 선택된 견종 ID와 이름을 변수에 저장
                        petSpeciesId = breed.id;
                        displaySelectedBreed(breed.species);
                        $('#breedModal').modal('hide'); // 모달 닫기
                    };
                    breedList.appendChild(li);
                });
            }
        })
        .catch((error) => {
            console.error('Error fetching breed list:', error);
        });
}

// 견종 등록 폼으로 전환 함수
const showBreedRegistrationForm = () => {
    // 검색 결과 없음 메시지와 검색 폼을 숨기고, 등록 폼을 표시합니다.
    document.getElementById('noResults').classList.add('d-none');
    document.getElementById('breedList').classList.add('d-none');
    document.getElementById('registrationForm').classList.remove('d-none');
}

const registerBreed = () => {
    const breedName = document.getElementById('newBreedName').value;

    if (!breedName) {
        alert('견종 이름을 입력해 주세요.');
        return;
    }

    axios.post('/v1/customers/pets/species', {
        query: breedName
    })
        .then((response) => {
            // 서버 응답에서 견종 ID를 받아옴
            alert('견종이 성공적으로 등록되었습니다.');
            petSpeciesId = response.data;
            // 방금 등록된 견종을 화면에 표시
            displaySelectedBreed(breedName);

            // 모달을 닫고 초기화
            document.getElementById('registrationForm').classList.add('d-none');  // 등록 폼 숨기기
            document.getElementById('breedList').classList.remove('d-none');      // 견종 리스트 표시
            document.getElementById('noResults').classList.add('d-none');         // '검색 결과 없음' 숨기기
            document.getElementById('searchBreed').value = '';  // 검색 필드 초기화
            $('#breedModal').modal('hide'); // 모달 닫기

        })
        .catch((error) => {
            console.error('Error registering breed:', error);
            alert('견종 등록에 실패했습니다.');
        });
}

const displaySelectedBreed = (newBreedName) => {
    const speciesDiv = document.getElementById('species');
    speciesDiv.textContent = newBreedName;  // 새로운 견종 이름을 #species div에 표시
}

const submitForm = (event) => {
    event.preventDefault();
    const form = document.getElementById('petForm');
    const formData = new FormData(form);

    // 추가 데이터 추가
    formData.append('speciesId', petSpeciesId);
    console.log(petId);
    formData.append('id',petId)

    // 파일 데이터 추가 (이미지가 선택된 경우)
    const fileInput = document.getElementById('image');
    if (fileInput.files.length > 0) {
        console.log("testFile");
        console.log(fileInput.files[0]);
        formData.append('petImage', fileInput.files[0]);
    }
    console.log(formData.get('petImage'));
    // Axios를 이용해 폼 데이터 전송
    axios.put('/v1/customers/pets/update', formData, {
        headers: {
            'Content-Type': 'multipart/form-data' // `FormData`를 사용할 때 필수
        }
    })
        .then((response) => {
            alert('펫이 성공적으로 등록되었습니다.');
            window.location.href = '/v1/customers/member';


            // 필요에 따라 추가 작업 수행
        })
        .catch((error) => {
            // 요청이 실패했을 때
            console.error('Error submitting form:', error);
            alert('폼 제출에 실패했습니다.');
        });
}
