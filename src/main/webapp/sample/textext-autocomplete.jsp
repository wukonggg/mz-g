<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="../component/jquery/plugins/textext/1.3.1/css/textext.core.css"/>
    <link rel="stylesheet" href="../component/jquery/plugins/textext/1.3.1/css/textext.plugin.autocomplete.css"/>
</head>
<body>

<%--<textarea id="textarea" class="example" rows="1" style="width: 500px">1111</textarea>--%>
<input id="textarea" type="text" style="width: 500px" value="">

<script src="../component/jquery/2.1.3/jquery.min.js"></script>
<script src="../component/jquery/plugins/textext/1.3.1/js/textext.core.js"></script>
<script src="../component/jquery/plugins/textext/1.3.1/js/textext.plugin.ajax.js"></script>
<script src="../component/jquery/plugins/textext/1.3.1/js/textext.plugin.autocomplete.js"></script>
<script>
    $(function () {
        $('#textarea').textext({
            plugins : 'autocomplete ajax',
            ajax : {
                //url : 'http://textextjs.com/manual/examples/data.json',
                url: 'http://localhost:8080/g/customer/cust/autocomplete.io',
                dataType : 'json',
                cacheResults : false
            }
        });
    });
<!--
        $('#textarea').textext({ plugins: 'tags autocomplete' }).bind('getSuggestions', function (e, data) {
            var list = [
                    'Basic',
                    'Closure',
                    'Cobol',
                    'Delphi',
                    'Erlang',
                    'Fortran',
                    'Go',
                    'Groovy',
                    'Haskel',
                    'Java',
                    'JavaScript',
                    'OCAML',
                    'PHP',
                    'Perl',
                    'Python',
                    'Ruby',
                    'Scala',
                    'GBin1'
                ],
                textext = $(e.target).textext()[0],
                query = (data ? data.query : '') || '';
            $(this).trigger(
                'setSuggestions',
                { result: textext.itemManager().filter(list, query) }
            );
        });
-->
</script>
</body>
</html>