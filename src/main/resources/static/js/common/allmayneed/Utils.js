/**
 * 工具函数
 */
Utils = {

    /**
     * 将表单数据转为json对象
     * @param formObject 表单节点对象
     * @return 表单数据对象
     */
    serializeFormObject: function (formObject) {
        //console.log(formObject[0])
        //数组形式的数据
        var dataArr = formObject.serializeArray();
        //要返回的表单对象
        var formDataObject = {};
        $.each(dataArr, function (index, obj) {
            formDataObject[obj.name] = obj.value;
        });
        return formDataObject;
    }
}