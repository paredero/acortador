<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cliente</title>
<link rel="stylesheet" href="./bootstrap.min.css"
	integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb"
	crossorigin="anonymous" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

</head>
<body>

	<div class="container">
		<form id="formulario" style="padding-top: 20px;">
			<div class="form-group row">
				<label for="absoluteUrl" class="col-sm-2 col-form-label">Url
					a acortar</label>
				<div class="col-sm-10">
					<input type="text" name="absoluteUrl" id="absoluteUrl"
						class="form-control" placeholder="introduzca la url" />
				</div>
				<input class="btn btn-primary" id="acortar" type="button"
                value="Acortar">
			</div>
			
		</form>
		<div class="row" id="urlAcortada"></div>
	</div>
	<script>
		var ruta = "${pageContext.request.contextPath}" + '/url';
		$(function() {
		    $("#acortar").click(enviarDatos);
		    $("#formulario").submit(function(e) {
		        enviarDatos();
		        e.preventDefault();
		    });
		});

		function enviarDatos() {
		    var settings = {
		        cache: false,
		        data: {
		            absoluteUrl: $("#absoluteUrl").val()
		        },
		        method: 'post',
		        success: function(data) {
		            if (data.urlAcortada) {
		                $("#urlAcortada").html('<a href="'+data.urlAcortada+'">'+data.urlAcortada+'</a>');
		            }
		        }
		    };
		    
		    $.ajax(ruta, settings);
		}
	</script>
</body>
</html>