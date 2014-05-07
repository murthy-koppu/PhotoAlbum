function loadImages() {
	var imagesList = new AlbumModel().getImagesList();
	var tBodyInnerHtml = "";
	for (var imageMdl in imagesList) {
		tBodyInnerHtml += "<tr><td> <img src='" + imagesList[imageMdl].src + "' onclick='loadImagePopup("+imageMdl+")' width='300' /><td style='text-align:center'>" + imagesList[imageMdl].title + "</td><td style='text-align:center'>" +
		 imagesList[imageMdl].description + "</td></tr>";
	}
	document.getElementById("imagesTableBody").innerHTML = tBodyInnerHtml;
}

function onPopupReady(){
	var imagesList = new AlbumModel().getImagesList();
	var query = window.location.search;
	var imageIndx = query.split('imageUrl=')[1] ? query.split('imageUrl=')[1] : 'http://eswalls.com/xitefun-most-beautiful-scenery-wallpapers-hd/';
	document.getElementById('imageEle').setAttribute('src', imagesList[imageIndx].src);
}

function loadImagePopup(imageMdlIndx){
	popup_window = window.open('../views/ImagePopup.html?imageUrl='+imageMdlIndx,"_blank","directories=no, status=no, menubar=no, scrollbars=no, resizable=no,width=600, height=560,top=120,left=180");
	popup_window.focus();
}