mapboxgl.accessToken = 'pk.eyJ1IjoiZGFuZ3RoZW1lbjEwIiwiYSI6ImNrcDhmMjZ4ZzA4M3Iyb21uZm5ibzI4NG8ifQ.vHjqFsqKMyj6q_tsmrYcNg';
var mapCenter = new mapboxgl.LngLat(139.72116702175174, 35.64997652994234);
var map = new mapboxgl.Map({
    container: 'map',
    style: 'mapbox://styles/mapbox/streets-v11',
    center: mapCenter,
    doubleClickZoom: true,
    zoom: 15
});
map.addControl(new mapboxgl.NavigationControl(), 'top-left');
var scale = new mapboxgl.ScaleControl({
  maxWidth: 80,
  unit: 'metric'
});
map.addControl(scale);
map.scrollZoom.setZoomRate(1/25);
map.addControl(
    new MapboxGeocoder({
        accessToken: mapboxgl.accessToken,
        mapboxgl: mapboxgl
    })
);

var marker = new mapboxgl.Marker({color: "red"})
  .setLngLat([0, 0])
  .addTo(map);

map.on('load', e => {
  createMarker(marker);
});

map.on('movestart', e => {
  createMarker(marker);
});

map.on('move', e => {
  createMarker(marker);
});

map.on('moveend', e => {
  createMarker(marker);
});


$(document).ready(()=>{
    $("#btn-download").click( () => {
         window.location.href = "/download/file";
    });

    $("#btn-jum").on('click', () => {
        let coordinate = $("#inputGroupSelect03").val();
        jumTo(coordinate);
    });

    $("#panTo").on('click', () => {
        let coordinate = $("#latLng").val();
        jumTo(coordinate);
    });
});
function getUser2(currentPage){
    $.ajax({
        url: `/student/page?page=${currentPage}`,
        success: function(result){
            $('#customerTable').replaceWith(result);
        },
    });
}

function jumTo(cdt){
    var coordinate = cdt.trim().split(",");
    var lngN = parseFloat(coordinate[0]);
    var latN = parseFloat(coordinate[1]);
    map.flyTo({
        center: [
            lngN,
            latN
        ],
        essential: true
    });
}

function createMarker(marker){
    marker.setLngLat(map.getCenter());
    var lngLat = marker.getLngLat();
    var lon = lngLat.lng;
    var lat = lngLat.lat;
    $("#center").val(`${lon}, ${lat}`);
}
