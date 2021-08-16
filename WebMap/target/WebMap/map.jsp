<%@ page import="it.uniroma2.ispw.c3s.maps.model.Icon" %>

<jsp:useBean id="mapBean" scope="session" class="it.uniroma2.ispw.c3s.maps.view.MapBean" />

<!DOCTYPE html>
<html>
<head>
    <title>Show icon on map</title>
    <link rel="stylesheet" href="css/ol.css" type="text/css">
    <link rel="stylesheet" href="css/samples.css" type="text/css">
</head>
<body>
<script src="js/ol.js"></script>
<div id="map" class="map"></div>
<script>
    var array_feature = [];
    <%
    for(Icon i : mapBean.getIconList()) {  %>
        var longitude = <%= i.getLatlong().getLongitude() %>;
        var latitude = <%= i.getLatlong().getLatitude() %>;
        var image_path = "<%= i.getImg().getImgPath() %>";

        //icon to add on the map
        var myIcon = new ol.Feature({
            geometry: new ol.geom.Point(ol.proj.fromLonLat([longitude, latitude])),
            name: 'Apartment'
        });

        myIcon.setStyle(new ol.style.Style({
            image: new ol.style.Icon({
                anchor: [0.5, 46],
                anchorXUnits: 'fraction',
                anchorYUnits: 'pixels',
                src: image_path
            })
        }));

        array_feature.push(myIcon);
        <%
    }
    %>

    // layerTile(tipo di mappa) and it.uniroma2.ispw.c3s.maps.view(center point and zoom)
    var layerOSM = new ol.layer.Tile({
        source: new ol.source.OSM()
    });
    var position= ol.proj.transform([<%=mapBean.getCenter().getLongitude()%>,
                                                    <%=mapBean.getCenter().getLatitude()%>], 'EPSG:4326', 'EPSG:3857');
    var view = new ol.View({
        center: position,
        zoom: <%=mapBean.getZoom()%>
    });

    var iconLayerSource = new ol.source.Vector({
        features: array_feature
    });

    var iconLayer = new ol.layer.Vector({
        source: iconLayerSource
    });

    // MAPPA
    var map = new ol.Map({
        target: 'map',
        layers: [layerOSM, iconLayer],
        view: view
    });

    // MOUSE POSITION
    var mousePosition = new ol.control.MousePosition({
        coordinateFormat: ol.coordinate.createStringXY(2),
        projection: 'EPSG:4326',
        target: document.getElementById('myposition'),
        undefinedHTML: '&nbsp;'
    });
    map.addControl(mousePosition);

    // SCALA
    var scale = new ol.control.ScaleLine({
        units: 'degrees',
        minWidth: 100
    });
    map.addControl(scale);
</script>
</body>
</html>