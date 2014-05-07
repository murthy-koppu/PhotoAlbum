var AlbumModel = function(){
	for(var k=1; k<=5;k++) {
		var imageMdl = new ImageModel("title_"+k,"description_"+k,"../images/"+k+"_scenery.jpg");
		this.imagesList.push(imageMdl);	
	}	
};
AlbumModel.prototype.imagesList = [];
AlbumModel.prototype.getImagesList = function(){
	return this.imagesList;
};