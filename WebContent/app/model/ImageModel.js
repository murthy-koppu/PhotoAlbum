var ImageModel  = function(title,description,imageSource){
	this.title = title;
	this.description = description;
	this.src = imageSource;
};

ImageModel.prototype.title='';
ImageModel.prototype.description='';
ImageModel.prototype.src='';

ImageModel.prototype.setTitle = function(title){
	this.title = title;
};

ImageModel.prototype.setDesc = function(description){
	this.description = description;
};

ImageModel.prototype.setSource = function(imageSource){
	this.src = imageSource;
};