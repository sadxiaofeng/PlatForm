<style>
    .main-content{
        /*padding:0 !important;*/
    }
    #editor-container{
        height:500px;
    }
</style>
<pre id="editor-container">

</pre>
<script src="../../static/js/src-noconflict/ace.js"></script>
<script>
    var codeEdit = ace.edit("editor-container");
    codeEdit.setTheme("ace/theme/tomorrow");
    codeEdit.session.setMode("ace/model/java");
</script>