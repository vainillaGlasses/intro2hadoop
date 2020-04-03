$(document).ready(function() {

  var datatable;
  var first = true;

  $("#noWebCrawler").on('click', function(){
    $.ajax({
      type: "POST",
      url: "php/executeBash.php",
      dataType: "html",
      success: function(data) {
          alert("Ejecución finalizada"+data);
      },
      error : function(data) {
          alert("Ha ocurrido un error." + JSON.stringify(data));
      }
    });
  });
  


});