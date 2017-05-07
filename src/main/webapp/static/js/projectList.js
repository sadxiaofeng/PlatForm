jQuery(document).ready(function($) {
    
    //加载项目列表
	initProjectTable();
    
});

/**
 * 初始化项目列表
 */
function initProjectTable() {
    initDataTable('projectTable');
    
    $('.dataTables_empty').html('<div><p>还没有创建任何项目！</p></div>');
	
    var tableInfo = $('#projectTable_info').html();
    if(tableInfo.indexOf('Showing 0 to 0 of 0 entries') >= 0) {
        $('#projectTable_info').html('');
        $('.paging_full_numbers').hide();
    }
}

/**
 * 创建项目列表
 * @param datas
 */
function buildProjectTable(datas) {
    var bodyHtml = "";
    
    var len = datas.length;
    for(var i = 0; i < len; i++) {
        var data = datas[i];
        bodyHtml += "<tr>";
        bodyHtml += "<td>" + data.id + "</td>";
        bodyHtml += "<td>" + data.name + "</td>";
        bodyHtml += "<td>" + data.deptName + "</td>";
        bodyHtml += "<td>" + data.projectDesc + "</td>";
        bodyHtml += "<td>" + data.createTime + "</td>";
        bodyHtml += "<td>" + data.creator + "</td>";
        bodyHtml += "<td>" + data.creatorNumber + "</td>";
        
        bodyHtml += "<td>";
        bodyHtml += "<button onclick=\"deletePrj('" + data.id + "')\" class=\"btn btn-secondary btn-icon btn-xs\" style=\"border-radius:3px;margin-right:20px; width:82px;\"><i class=\"fa-trash\"></i><span style=\"margin-left:10px;\">删除项目</span></button>";
        bodyHtml += "</td>";
        
        bodyHtml += "</tr>";
    }
    
    $('#projectTable').dataTable().fnClearTable(); 
    $('#projectTable').dataTable().fnDestroy(); 
    
    $("#projectTable tbody").append(bodyHtml);
    
    initProjectTable();
}

/**
 * 删除项目
 * @param appId
 */
function deletePrj(prjId) {
    toastr.clear();
    confirm('确认删除', '确定要删除项目吗？', function() {
        
        request('../mvc/prjManager/deletePrj', 'prjId=' + prjId, function(result) {
            if(result.success) {
                buildProjectTable(result.data);
                toastr.success('删除项目成功!');
                window.location.href = '../mvc/projectList';
            } else {
                toastr.error(result.msg);
            }

            hideLoading('mainContent');
        });
    });
}


function confirm(title0, content0, func) {
$.confirm({
	title: title0,
    icon: 'fa-info-circle',
    content: '<br>&nbsp;&nbsp;' + content0,
    animationBounce: 2,
    confirmButton: '<h1>确认</h1>',
    cancelButton: '<h1>关闭</h1>',
	confirmButtonClass: 'btn-info',
    cancelButtonClass: 'btn-danger',
    confirm: func,
    cancel: function(){
    },
});
}

