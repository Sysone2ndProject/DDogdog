let debounceTimer;
let petSpeciesId; // petSpeciesId 변수를 선언합니다.
const handleFileSelect = (event) => {
    const file = event.target.files[0];
    const imagePreview = document.getElementById('imagePreview');

    if (file) {
        const reader = new FileReader();
        reader.onload = (e) => {
            imagePreview.innerHTML = `<img src="${e.target.result}" alt="Preview" class="image-preview">`;
        };
        reader.readAsDataURL(file);
    } else {
        imagePreview.innerHTML = ''; // 파일이 선택되지 않았을 때
    }
}

// 견종 리스트를 SweetAlert2 모달로 로드
const loadBreedList = (query) => {
    clearTimeout(debounceTimer); // 이전 타이머 클리어

    debounceTimer = setTimeout(() => {
        if (!query) {
            const breedListElement = document.getElementById('breedList');
            breedListElement.innerHTML = `<p class="text-center">검색어를 입력해주세요</p>`;
            return;
        }

        axios.get('/v1/customers/pets/species', {
            params: { query: query }
        })
        .then((response) => {
            let breedListHtml = '';
            if (response.data.length === 0) {
                breedListHtml = `
                    <p class="text-center">검색 결과 없음</p>
                    <p class="text-center">
                        <a href="#" onclick="showBreedRegistrationForm()" class="link-style"">견종 등록하기</a>
                    </p>`;

            } else {
                response.data.forEach((breed) => {
                    breedListHtml += `<li class="list-group-item" onclick="selectBreed('${breed.id}', '${breed.species}')">${breed.species}</li>`;
                });
            }

            // 모달의 breedList 업데이트
            const breedListElement = document.getElementById('breedList');
            breedListElement.innerHTML = breedListHtml;
        })
        .catch((error) => {
            console.error('견종 리스트 가져오기 오류:', error);
        });
    }, 300); // 300ms 후에 요청
};

// 견종 선택 시 호출되는 함수
const selectBreed = (id, species) => {
    petSpeciesId = id;
    displaySelectedBreed(species);
    Swal.close(); // SweetAlert 모달 닫기
};

// 견종 등록 모달을 SweetAlert2로
const showBreedRegistrationForm = () => {
    Swal.fire({
        title: '견종 등록',
        html: `<input type="text" id="newBreedName" class="swal2-input" placeholder="새로운 견종 이름">`,
        showCancelButton: true,
        confirmButtonText: '등록',
        preConfirm: () => {
            const breedName = document.getElementById('newBreedName').value;
            if (!breedName) {
                Swal.showValidationMessage('견종 이름을 입력해 주세요.');
            } else {
                return breedName;
            }
        }
    }).then((result) => {
        if (result.isConfirmed) {
            registerBreed(result.value); // 견종 등록
        }
    });
};

// 견종 등록 처리 함수
const registerBreed = (breedName) => {
    axios.post('/v1/customers/pets/species', {
        query: breedName
    })
    .then((response) => {
        Swal.fire('등록 완료', '견종이 성공적으로 등록되었습니다.', 'success');
        petSpeciesId = response.data; // 새로운 견종 ID 저장
        displaySelectedBreed(breedName); // 견종 화면에 표시
    })
    .catch((error) => {
        console.error('견종 등록 오류:', error);
        Swal.fire('등록 실패', '견종 등록에 실패했습니다.', 'error');
    });
};

// 선택한 견종을 화면에 표시
const displaySelectedBreed = (newBreedName) => {
    const speciesDiv = document.getElementById('species');
    speciesDiv.textContent = newBreedName;
};


const submitForm = (event) => {
    event.preventDefault();
    const form = document.getElementById('petForm');
    const formData = new FormData(form);

    // 추가 데이터
    if (petSpeciesId == null || petSpeciesId === ""){
        formData.append('speciesId', defaultPetSpeciesId);
    }else{
        formData.append('speciesId', petSpeciesId);
    }

    formData.append('id',petId);

    // 파일 데이터 추가 (이미지가 선택된 경우)
    const fileInput = document.getElementById('image');
    if (fileInput.files.length > 0) {
        formData.append('petImage', fileInput.files[0]);
    }else{
        formData.append('existingImageUrl', existingImageUrl);
    }


    // Axios를 이용해 폼 데이터 전송
    axios.put('/v1/customers/pets/update', formData, {
        headers: {
            'Content-Type': 'multipart/form-data' // `FormData`를 사용할 때 필수
        }
    })
        .then((response) => {
            Swal.fire({
                title: '수정 완료',
                text: '수정되었습니다.',
                icon: 'success',
            }).then((result) => {
                if (result.isConfirmed) {
                    // 확인 버튼을 클릭했을 때 URL 변경
                    window.location.href = '/v1/customers/member';
                }
            });
            // 필요에 따라 추가 작업 수행
        })
        .catch((error) => {
            // 요청이 실패했을 때
            console.error('Error submitting form:', error);
            alert('폼 제출에 실패했습니다.');
        });
}
// SweetAlert2 모달을 띄우고 견종 리스트 로드
const openBreedSelectionModal = () => {
    loadBreedList(''); // 초기 로드
    Swal.fire({
        title: '견종 선택',
        html: `
      <div style="position: relative;">
        <input id="searchBreed" class="swal2-input" placeholder="견종 검색" oninput="loadBreedList(this.value)">
        <button id="searchButton" style="background: none; border: none; cursor: pointer; position: absolute; right: 10px; top: 50%; transform: translateY(-50%);">
          <i class="fas fa-search"></i>
        </button>
      </div>
      <ul id="breedList" class="list-group"></ul>
    `,
        showCancelButton: true,
        focusConfirm: false,
    });
};

const navigateToPage = () => {
    window.location.href = '/v1/customers/member';  // 이동할 URL
};