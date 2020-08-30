Vue.component('mynavbar', {
	    template:' <nav class="navbar navbar-expand-lg navbar-dark bg-dark static-top">'+
	    '<div class="container">'+
	      '<a class="navbar-brand" href="/Deployment/home/eng/">Resource Management System</a>'+
	      '<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="true" aria-label="Toggle navigation">'+
	        '<span class="navbar-toggler-icon"></span>'+
	      '</button>'+
	      '<div class="collapse navbar-collapse" id="navbarResponsive">'+
	        '<ul class="navbar-nav ml-auto">'+
	                      '<li class="nav-item">'+

	            

	            


	            '<a class="nav-link" href="/Deployment/home/eng/">'+

	             ' Home'+
	              '<span class="sr-only">(current)</span>'+
	            '</a>'+
	                    '<li class="nav-item">'+
	            '<a class="nav-link" href="#">Control Board</a>'+
	          '</li>'+
	          '<li class="nav-item">'+
	            '<a class="nav-link" href="{% url '+'finance:index'+' %}"> Financial System</a>'+
	          '</li>'+
	           '<li class="nav-item">'+
	            '<a class="nav-link" href="hrs">Human Resources System</a>'+
	          '</li>'+
	          '<li class="nav-item">'+
	            '<a class="nav-link" href="{% url '+'initiative:index'+' %}">Initiatives</a>'+
	          '</li>'+
	             '<li class="nave-item"><a class="nav-link" href="login"> Login  </a></li>'+
	             '<li class="nave-item"><a class="nav-link" href="/Deployment/home/ar/"> العربية   </a></li>'+
	                         

	           
	        '</ul>'+
	      '</div>'+
	   '</div>'+
	  '</nav>' });

	  Vue.component('myfooter', {
	    template:' <div> '+
	'<div class="container-fluid padding"> '+
	        '<div class="row text-center"> '+
	            '<div class="col-md-4"> '+
	               '<hr class="light"> '+
	                '<p>About KACST</p> '+

	            '</div> '+
	            '<div class="col-md-4"> '+
	                '<hr class="light"> '+
	                '<p>Policies</p> '+
	                '<hr class="light"> '+

	            '</div> '+
	            '<div class="col-md-4"> '+
	                '<hr class="light"> '+
	                '<p>Innovation</p> '+
	                '<hr class="light"> '+

	            '</div> '+
	            '<div class="col-12 footer-copyright"> '+
	                '<br> '+
	                '<p> King Abdulaziz City for Science and Technology &copy; 2020</p> '+
	            '</div> '+

	        '</div> '+
	    '</div> '+
	'</div>'});

	  
	  
	  Vue.component('myheader',{
		  template: '<div id="header"></div>'
	  });
	  
	  Vue.component('myjs',{
		  template: '<div id="js"></div>'
	  });
	  
	  Vue.component('mycontent',{
		  template: '<div id="content"></div>'
	  });

	  Vue.component('mypagination',{
		  template: '<div id="pagination"></div>'
	  });
	  

	  
	  
	  
	  var app = new Vue({
	    el:'#app'
	  })