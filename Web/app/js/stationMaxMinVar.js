$(document).ready(function() {

  var datatable;
  var first = true;

  //Cargar continentes
  $.ajax({
      type: "POST",
      url: "php/getContinents.php",
      dataType: "html",
      success: function(data) {
          $("#continents").html(data);
      },
      error : function(data) {
          alert("Ha ocurrido un error." + JSON.stringify(data));
      }
    });

  //Cargar paises
  function getCountries(){
    $.ajax({
      type: "POST",
      url: "php/getCountries.php",
      dataType: "html",
      success: function(data) {
          $("#countries").html(data);
      },
      error : function(data) {
          alert("Ha ocurrido un error." + JSON.stringify(data));
      }
    });
  }

  getCountries();

  //Cargar paises
  function getStations(){
    $.ajax({
      type: "POST",
      url: "php/getStations.php",
      dataType: "html",
      success: function(data) {
          $("#stations").html(data);
      },
      error : function(data) {
          alert("Ha ocurrido un error." + JSON.stringify(data));
      }
    });
  }

  getStations();
  

  //Cargar variables
  $.ajax({
      type: "POST",
      url: "php/getVariables.php",
      dataType: "html",
      success: function(data) {
          $("#variables").html(data);
      },
      error : function(data) {
          alert("Ha ocurrido un error." + JSON.stringify(data));
      }
    });

  function getContinentStations(){
    var continentId = $("#continents").val();
    $.ajax({
        type: "POST",
        url: "php/getContinentStations.php",
        dataType: "html",
        data: {
          continentId:continentId
        },
        success: function(data) {
            $("#stations").html(data);
        },
        error : function(data) {
            alert("Ha ocurrido un error." + JSON.stringify(data));
        }
      });
  }


  //Cargar países de un continente
  $("#continents").on('change', function(){    
    var continentId = $("#continents").val();
    console.log(continentId);
    if (continentId == "all"){
      getCountries();
      getStations();
    } else {
      $.ajax({
        type: "POST",
        url: "php/getContinentCountries.php",
        dataType: "html",
        data: {
          continentId:continentId
        },
        success: function(data) {
            $("#countries").html(data);
        },
        error : function(data) {
            alert("Ha ocurrido un error." + JSON.stringify(data));
        }
      });
      getContinentStations();
    }
  })


  //Cargar estaciones de un país
  $("#countries").on('change', function(){    
    var countryId = $("#countries").val();
    var continentId = $("#continents").val();
    if (countryId == "all" && continentId == "all"){
      getStations();
    } else if (countryId == "all" && continentId != "all") {
      getContinentStations();
    } else {
      $.ajax({
        type: "POST",
        url: "php/getCountryStations.php",
        dataType: "html",
        data: {
          countryId:countryId
        },
        success: function(data) {
            $("#stations").html(data);
        },
        error : function(data) {
            alert("Ha ocurrido un error." + JSON.stringify(data));
        }
      });
    }
  })

  var handleDataTableButtons = function() {
    if ($("#datatable-buttons").length) {
      datatable = $("#datatable-buttons").DataTable({
        ajax:{
          "url":"php/getStationMaxMinVarTable.php",
          "data":function (d) {
              d.first = first;
              d.continentId = $("#continents").val();
              d.countryId = $("#countries").val();
              d.stationId = $("#stations").val();
              d.variableId = $("#variables").val();
              d.maxMin = $("#maxMin").val();
          },
          "type":"post"
        },
        "autoWidth": false,
        dom: "Bfrtip",
        buttons: [
          {
            extend: "copy",
            className: "btn-sm"
          },
          {
            extend: "csv",
            className: "btn-sm"
          },
          {
            extend: "excel",
            className: "btn-sm"
          },
          {
            extend: "pdf",
            className: "btn-sm"
          },
          {
            extend: "print",
            className: "btn-sm"
          },
        ],
        responsive: true,
        aaSorting: [],
        pageLength: 11
      });
    }
  };

  TableManageButtons = function() {
    "use strict";
    return {
      init: function() {
        handleDataTableButtons();
      }
    };
  }();

  TableManageButtons.init();

  $("#filterForm").on('submit', function(){
    first = false;
    datatable.ajax.reload();
    return false;
  });

});