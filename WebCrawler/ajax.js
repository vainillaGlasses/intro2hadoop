$().ready(function(){

	$("#check").on('click', function(){
		$.ajax({
		dataType: 'html',
		type: 'post',
		url: 'validate.php',
		success: function(data){
			alert(data)
		},
		error: function (data){
			alert(data)
		}
	});
	})

});