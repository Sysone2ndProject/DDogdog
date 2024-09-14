document.getElementById("uploadForm").onsubmit = function (event) {
    event.preventDefault();  // 기본 폼 제출 방지

    let formData = new FormData();
    let fileInput = document.getElementById("image");
    formData.append("image", fileInput.files[0]);

    axios.post('/s3/upload', formData, {
        headers: {
            'Content-Type': 'multipart/form-data'
        }
    })
        .then(response => {
            alert("File uploaded successfully: " + response.data);
            console.log(response.data)
        })
        .catch(error => {
            alert("Failed to upload the file.");
            console.error("Error:", error);
        });
};
