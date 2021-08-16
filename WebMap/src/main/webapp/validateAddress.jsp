<%@ page import="java.util.regex.Pattern" %>
<%@ page import="it.uniroma2.ispw.c3s.maps.controller.GeocodingAsyncTask" %>
<%@ page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

<!-- Controllo che l'utente abbia richiesto tramite bottone di validare un indirizzo -->
<%
    String exist ="";
    String address="";
    String coord ="";
    String city = "";
    String check_city = "";
    String road = "";
    String nr ="";
    String check_road_nr1="";
    String check_road_nr2="";
    String cap="";
    String check_cap="";
    if (request.getParameter("validateAddressSubmit") != null) { %>
        <jsp:useBean id="geocodeBean" scope="request" class="it.uniroma2.ispw.c3s.maps.view.GeocodeBean"/>
    <%  city = request.getParameter("city");
        road = request.getParameter("road");
        nr = request.getParameter("nr");
        cap = request.getParameter("cap");
        boolean flag = true;

        if (!(Pattern.matches("[a-zA-Z]+", road) || Pattern.matches("[a-zA-Z]+\\s+[a-zA-Z]+",road) || road.isEmpty())) {
            check_road_nr1 = "Valori non validi!";
            flag = false;
        } else {
            check_road_nr1 = "";
            if (!road.isEmpty()) {
                address = "via " + road + " ";
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
        if (!(Pattern.matches("[0-9]+", cap) || cap.isEmpty())) {
            check_cap = "Valore non valido!";
            flag = false;
        } else {
            check_cap = "";
            if (!cap.isEmpty()) {
                address = address + " " + cap;
            }
        }
        if (!flag) {
        } else {
            coord = "Le coordinate per "+address+" sono:";
            geocodeBean.setAddress(address);

            GeocodingAsyncTask gat = new GeocodingAsyncTask(geocodeBean.getAddress());
            gat.start();
            // wait at most 3 secs for the answer
            try {
                gat.join(3000);
            } catch (InterruptedException e) {
                    %> <script>
                    alert("Errore di sistema!");
                     </script>     <%
            }
            geocodeBean.setPoint(gat.getLatLng());

            if (geocodeBean.getPoint() == null) {
                coord="";
                exist = "Indirizzo non valido o connessione scaduta!";
            } else {
                exist = "Lat : "+geocodeBean.getPoint().getLatitude()+" Lon : "+geocodeBean.getPoint().getLongitude();
            }
        }

    }
%>

<!DOCTYPE html>
<style type="text/css">
    .button {background-color:white;border-radius:5px;border:
                                                        1px solid #D3D3D3; font-size:16px;position:relative;width:15%}
    .button:hover {background-color:#007AFF;color:white}
</style>
<html><head>
    <title>F.E.R.S.A.</title>
</head>
<body class="Sito mappa">
<div class="container" align="center" style="font-size:16px;font-family:Tahoma, Geneva, sans-serif !important; margin-top:10%">
    <form action="validateAddress.jsp" name="UseCase1Form" method="POST">
        <font size="5">Inserisci i dati richiesti</font>
        <br>
        <br>
        <br>
        Città*:
        <input type="text" name="city" value="<%=city%>">
        <br>
        <font color="red"><%=check_city%></font>
        <br>
        Via:
        <input type="text" name="road" value="<%=road%>">
        Nr:
        <input type="text" name="nr" value="<%=nr%>">
        <br>
        <font color="red"><%=check_road_nr1%><%=check_road_nr2%></font>
        <br>
        CAP:
        <input type="text" name="cap" value="<%=cap%>">
        <br>
        <font color="red"><%=check_cap%></font>
        <br>
        <input class=button id="validateAddressSubmit" type="submit"
                                                                name="validateAddressSubmit" value="Valida Indirizzo">
    </form>
    <br>
    <%= coord %>
    <br>
    <%= exist %>
    <br>
    <br>
    <button class="button" type="button" onclick="location.href='index.html'">Indietro</button>
</div>

</body></html>