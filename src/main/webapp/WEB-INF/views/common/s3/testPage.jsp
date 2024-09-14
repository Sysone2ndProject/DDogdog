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
<script src="/js/common/s3/testPage.js"></script>
</body>
</html>