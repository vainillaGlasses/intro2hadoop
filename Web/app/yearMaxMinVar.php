<!DOCTYPE html>
<html lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Máximos y Mínimos por Año</title>

    <!-- Bootstrap -->
    <link href="../vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="../vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="../vendors/nprogress/nprogress.css" rel="stylesheet">
    <!-- iCheck -->
    <link href="../vendors/iCheck/skins/flat/green.css" rel="stylesheet">
    <!-- bootstrap-progressbar -->
    <link href="../vendors/bootstrap-progressbar/css/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet">
    <!-- JQVMap -->
    <link href="../vendors/jqvmap/dist/jqvmap.min.css" rel="stylesheet"/>

    <!-- Custom Theme Style -->
    <link href="../build/css/custom.min.css" rel="stylesheet">
  </head>

  
        <?php include('headerAndSidebar.php') ?>
            

        <!-- page content -->
        <div class="right_col" role="main">
          
          <div class="row">
            <div class="page-title">
              <div class="title">
                <h3>Para cada país el año en el que cada una de las variables fue la máxima/mínima</h3>
              </div>
            </div>
            <div class="clearfix"></div>
            <br>

            <div class="row">
              <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel">
                  <div class="x_title">
                    <h2>Filtros</h2>
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">

                    <form class="form-horizontal form-label-left" novalidate id="filterForm" autocomplete="off">
                      <div class="form-group">
                        <label class="control-label col-md-1 col-sm-1 col-xs-12">Continente</label>
                        <div class="col-md-2 col-sm-2 col-xs-12">
                          <select class="form-control" id="continents">
                          </select>
                        </div>
                        <label class="control-label col-md-1 col-sm-1 col-xs-12">País</label>
                        <div class="col-md-2 col-sm-2 col-xs-12">
                          <select class="form-control" id="countries">
                          </select>
                        </div>
                        <label class="control-label col-md-1 col-sm-1 col-xs-12">Variable</label>
                        <div class="col-md-2 col-sm-2 col-xs-12">
                          <select class="form-control" id="variables">
                          </select>
                        </div>
                        <label class="control-label col-md-1 col-sm-1 col-xs-12">Min-Max</label>
                        <div class="col-md-2 col-sm-2 col-xs-12">
                          <select class="form-control" id="maxMin">
                            <option value="both">Ambos</option>
                            <option value="Min">Mínimos</option>
                            <option value="Max">Máximos</option>
                          </select>
                        </div>
                      </div>
                      <div class="form-group">
                        <div class="col-md-6 col-md-offset-5">
                          <button id="send" type="submit" class="btn btn-success">Aplicar</button>
                        </div>
                      </div>
                    </form>
                  </div>
                </div>
              </div>
            </div>
          </div>

          </br>
          <div class="row" id="tableDiv">
            <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel">
                  <div class="x_title">
                    <h2>Tabla</small></h2>
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                    <table id="datatable-buttons" class="table table-striped table-bordered">
                      <thead>
                        <tr>
                          <th>Continente</th>
                          <th>País</th>
                          <th>Año</th>
                          <th>Variable</th>
                          <th>Valor</th>
                          <th>Max/Min</th>
                        </tr>
                      </thead>


                      <tbody>
                    
                      </tbody>
                    </table>
                  </div>
                </div>
                <br>
        </div>
        <!-- /page content -->

        <!-- footer content -->
        <footer>
          <div class="pull-right">
            Gentelella - Bootstrap Admin Template by <a href="https://colorlib.com">Colorlib</a>
          </div>
          <div class="clearfix"></div>
        </footer>
        <!-- /footer content -->
      </div>
    </div>

    <!-- jQuery -->
    <script src="../vendors/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap -->
    <script src="../vendors/bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- FastClick -->
    <script src="../vendors/fastclick/lib/fastclick.js"></script>
    <!-- NProgress -->

    <!-- Custom Theme Scripts -->
    <script src="../build/js/custom.min.js"></script>

    <!-- Datatables -->
    <script src="../vendors/datatables.net/js/jquery.dataTables.min.js"></script>
    <script src="../vendors/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
    <script src="../vendors/datatables.net-buttons/js/dataTables.buttons.min.js"></script>
    <script src="../vendors/datatables.net-buttons-bs/js/buttons.bootstrap.min.js"></script>
    <script src="../vendors/datatables.net-buttons/js/buttons.flash.min.js"></script>
    <script src="../vendors/datatables.net-buttons/js/buttons.html5.min.js"></script>
    <script src="../vendors/datatables.net-buttons/js/buttons.print.min.js"></script>
    <script src="../vendors/datatables.net-keytable/js/dataTables.keyTable.min.js"></script>
    <script src="../vendors/datatables.net-responsive/js/dataTables.responsive.min.js"></script>
    <script src="../vendors/datatables.net-responsive-bs/js/responsive.bootstrap.js"></script>
    <script src="../vendors/datatables.net-scroller/js/datatables.scroller.min.js"></script>
    <script src="../vendors/jszip/dist/jszip.min.js"></script>
    <script src="../vendors/pdfmake/build/pdfmake.min.js"></script>
    <script src="../vendors/pdfmake/build/vfs_fonts.js"></script>

    <!-- JS For Followers -->
    <script src="js/yearMaxMinVar.js"></script>
  </body>
</html>
