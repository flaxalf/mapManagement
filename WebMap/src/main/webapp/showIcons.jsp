<%@ page import="it.uniroma2.ispw.c3s.maps.model.Map" %>
<%@ page import="it.uniroma2.ispw.c3s.maps.controller.ShowIconsController" %>
<%@ page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

<!-- Controllo che l'utente abbia richiesto tramite bottone di mostrare la mappa con le icone -->
<%
    if (request.getParameter("showExampleSubmit") != null) { %>
        <jsp:useBean id="mapBean" scope="session" class="it.uniroma2.ispw.c3s.maps.view.MapBean"/>
    <%
        int value_icons = Character.getNumericValue(request.getParameter("showExampleSubmit").charAt(0));
        Map mv = ShowIconsController.showIcons(value_icons);
        mapBean.setIconList(mv.getIconList());
        mapBean.setCenter(mv.getCenter());
        mapBean.setZoom(mv.getZoom());
    %>
    <jsp:forward page="map.jsp" />
    <%
    }
%>

<!DOCTYPE html>
<style type="text/css">
    .button {background-color:white;border-radius:5px;border:
                                                        1px solid #D3D3D3; font-size:16px;position:relative;width:20%}
    .button:hover {background-color:#007AFF;color:white}
</style>
<html><head>
    <title>F.E.R.S.A.</title>
</head>
<body class="Sito mappa">
<div class="container" align="center" style="font-size:16px;font-family:Tahoma, Geneva, sans-serif !important; margin-top:10%">
    <form action="showIcons.jsp" name="UseCase1Form" method="POST">
        <font size="5">Cliccare sui bottoni sottostanti per <br> visualizzare l'icona corrispondente in mappa</font>
        <br>
        <br>
        <br>
        <input class="button" id="showExample1Submit" type="submit"
                                                        name="showExampleSubmit" value="1. Appartamenti e Loft di Roma">
        <br>
        <br>
        <input class="button" id="showExample2Submit" type="submit"
                                                    name="showExampleSubmit" value="2. Appartamenti e Loft di Milano">
        <br>
        <br>
        <input class="button" id="showExample3Submit" type="submit"
                                                    name="showExampleSubmit" value="3. Appartamenti e Loft di Napoli">
    </form>
    <br>
    <br>
    <button class="button" type="button" onclick="location.href='index.html'">Indietro</button>
</div>

</body></html>