<%@ page import="it.uniroma2.ispw.c3s.maps.model.*" %>
<%@ page import="it.uniroma2.ispw.c3s.maps.controller.ValidateAddressController" %>
<%@ page import="it.uniroma2.ispw.c3s.maps.controller.HighlightIconsByZoneController" %>
<%@ page import="it.uniroma2.ispw.c3s.maps.model.Map" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.regex.Pattern" %>
<%@ page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

<!-- Controllo che l'utente abbia richiesto tramite bottone di mostrare la mappa con l'icona -->
<%
    String exist = "";
    String address = "";
    String city = "";
    String check_city = "";
    String road = "";
    String nr = "";
    String check_road_nr1 = "";
    String check_road_nr2 = "";
    if (request.getParameter("HighlightByZoneSubmit") != null) { %>
        <jsp:useBean id="infoHighlightBean" scope="session" class="it.uniroma2.ispw.c3s.maps.view.InfoBean"/>
        <jsp:useBean id="mapBean" scope="session" class="it.uniroma2.ispw.c3s.maps.view.MapBean"/>
        <jsp:setProperty name="infoHighlightBean" property="*" />
    <%
        city = infoHighlightBean.getCity();
        if (city == null) {
            city = "";
        }
        road = infoHighlightBean.getRoad();
        if (road == null) {
            road = "";
        }
        nr = infoHighlightBean.getNr();
        if (nr == null) {
            nr = "";
        }
        boolean flag = true;

        if (!(Pattern.matches("[a-zA-Z]+", road) || Pattern.matches("[a-zA-Z]+\\s+[a-zA-Z]+",road) || road.isEmpty())) {
            check_road_nr1 = "Valori non validi!";
            flag = false;
        } else {
            check_road_nr1 = "";
            if (!road.isEmpty()) {
                address = "via" + road + " ";
            }
        }
        if (!(Pattern.matches("[0-9]+", nr) || nr.isEmpty())) {
            check_road_nr1 = "Valori non validi!";
            flag = false;
        } else if (!nr.isEmpty() && (road.isEmpty() || !(Pattern.matches("[a-zA-Z]+", road) ||
                Pattern.matches("[a-zA-Z]+\\s+[a-zA-Z]+",road)))) {
            check_road_nr2 = "Inserire via!";
            flag = false;
        } else {
            check_road_nr2 = "";
            if (!nr.isEmpty()) {
                address = address + nr + " ";
            }
        }
        if (!(Pattern.matches("[a-zA-Z]+", city) || Pattern.matches("[a-zA-Z]+\\s+[a-zA-Z]+",city) || city.isEmpty())) {
            check_city = "Valore non valido!";
            flag = false;
        } else if (city.isEmpty()) {
            check_city = "Valore obbligatorio!";
            flag = false;
        } else {
            check_city = "";
            address = address + city;
        }
        if (!flag) {
        } else {
            String type = infoHighlightBean.getType();
            LatLng center = ValidateAddressController.geocodeAddress(address);
            try {
                if(center != null) {
                    Map mv = HighlightIconsByZoneController.highlightIconsByZone(center, type.toLowerCase());
                    mapBean.setIconList(mv.getIconList());
                    mapBean.setCenter(mv.getCenter());
                    mapBean.setZoom(mv.getZoom());
                    %>
                        <jsp:forward page="map.jsp"/>
                    <%
                } else{
                    exist = "Indirizzo non trovato, controllare i dati!";
                }
            } catch (ClassNotFoundException e) {
                %> <script>
                        alert("JDBC driver non trovato!");
                   </script>     <%
            } catch (SQLException e){
                %> <script>
                    alert("Errore nella comunicazione con il database!");
                </script>     <%
            }
        }
    }
%>

<!DOCTYPE html>
<style type="text/css">
    .button {background-color:white;border-radius:5px;border: 1px solid #D3D3D3;
                                                                            font-size:16px;position:relative;width:15%}
    .button:hover {background-color:#007AFF;color:white}
</style>
<html><head>
    <title>F.E.R.S.A.</title>
</head>
<body class="Sito mappa">
<div class="container" align="center" style="font-size:16px;font-family:Tahoma, Geneva, sans-serif !important; margin-top:10%">
    <form action="highlightIconsByZone.jsp" name="UseCase1Form" method="POST"><font size="6px">
                                                                                      Inserisci i dati richiesti</font>

        <br>

        <br><br>
        Città*:
        <input type="text" name="city" value="<%=city%>">
        <br>
        <font color="red"><%=check_city%></font>
        <br>
        Tipo*:
        <select name="type" style="font-size:17px;">
            <option value="Appartamento">Appartamento</option>
            <option value="Negozio">Negozio</option>
            <option value="Loft">Loft</option>
        </select>
        <br>
        <br>
        Via:
        <input type="text" name="road" value="<%=road%>">
        Nr:
        <input type="text" name="nr" value="<%=nr%>">
        <br>
        <font color="red"><%=check_road_nr1%><%=check_road_nr2%></font>
        <br>
        <input class="button" id="HighlightByZoneSubmit" type="submit" name="HighlightByZoneSubmit" value="Cerca">
    </form>
    <%= exist %>
    <br>
    <br>
    <button class="button" type="button" onclick="location.href='index.html'">Indietro</button>
</div>

</body></html>