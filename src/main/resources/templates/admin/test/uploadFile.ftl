<!DOCTYPE>
<html>
<head>
    <meta>
    <#include '../depend/_head.ftl'>
    <title>1213</title>
</head>
<body>

<form>
    <input id="file" type="file" name="file">
    <input id="removeFile" class="remove" type="text" name="key">
    <input id="submit" type="button" value="submit">
    <input id="remove" type="button" value="remove">
    <input id="removeMore" type="button" value="removeMore">
</form>

<script>
    $(document).on('click', '#removeMore', function () {
        var data = [
            '4803c5f3-49cf-45f5-91dd-bf5148bd9cd0',
            'bd297241-a37f-4997-b1de-1453814d8a6f'
        ];
        $.ajax({
            url: '/testa/removeFiles',
            type: 'post',
            data: {keysList: data},
            success: function (data) {
                console.log(data)
            }
        });
    })
    $(document).on('click', '#remove', function () {
        $.ajax({
            url: '/testa/removeFile',
            type: 'post',
            data: {key: $('#removeFile').val()},
            success: function (data) {
                console.log(data)
            }
        });
    })
    $(document).on('click', '#submit', function () {
        var formData = new FormData();
        formData.append('file', $('#file').get(0).files[0]);
        $.ajax({
            url: '/testa/upFile',
            type: 'post',
            data: formData,
            cache: false,
            processData: false,
            contentType: false,
            success: function (data) {
                console.log(data)
            }
        });
    })
</script>
</body>
</html>