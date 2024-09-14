<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>File Upload</title>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
</head>
<body>
<form id="uploadForm">
    <label for="image">Upload Image:</label>
    <input type="file" id="image" name="image" required />
    <button type="submit">Upload</button>
</form>

<script>
    document.getElementById("uploadForm").onsubmit = function(event) {
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
</script>
</body>
</html>