Vue.component('mynavbar', {
    template:' <nav class="navbar navbar-expand-lg navbar-dark bg-dark static-top">'+
    '<div class="container">'+
      '<a class="navbar-brand" href="/Deployment/home/ar/">نظام إدارة الموارد</a>'+
      '<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="true" aria-label="Toggle navigation">'+
        '<span class="navbar-toggler-icon"></span>'+
      '</button>'+
      '<div class="collapse navbar-collapse" id="navbarResponsive">'+
        '<ul class="navbar-nav ml-auto">'+
                      '<li class="nav-item active">'+

            

               
                '<li class="nave-item"><a class="nav-link" href="{% url '+'log:logout'+' %}"> للخروج   </a></li>'+

           


            '<a class="nav-link" href="/Deployment/home/ar/">'+

             ' الرئيسية'+
              '<span class="sr-only">(current)</span>'+
            '</a>'+
                    '<li class="nav-item">'+
            '<a class="nav-link" href="#">لوحة التحكم</a>'+
          '</li>'+
          '<li class="nav-item">'+
            '<a class="nav-link" href="{% url '+'finance:index'+' %}"> النظام المالي</a>'+
          '</li>'+
           '<li class="nav-item">'+
            '<a class="nav-link" href="hrs">نظام الموارد البشرية</a>'+
          '</li>'+
          '<li class="nav-item">'+
            '<a class="nav-link" href="{% url '+'initiative:index'+' %}">المبادرات</a>'+
          '</li>'+
             '<li class="nave-item"><a class="nav-link" href="login"> تسجيل دخول    </a></li>'+

             '<li class="nave-item"><a class="nav-link" href="/Deployment/home/eng/"> English   </a></li>'+           

           
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
                '<p>عن المدينة</p> '+

            '</div> '+
            '<div class="col-md-4"> '+
                '<hr class="light"> '+
                '<p>السياسات</p> '+
                '<hr class="light"> '+

            '</div> '+
            '<div class="col-md-4"> '+
                '<hr class="light"> '+
                '<p>الابتكار</p> '+
                '<hr class="light"> '+

            '</div> '+
            '<div class="col-12 footer-copyright"> '+
                '<br> '+
                '<p> مدينة الملك عبدالعزيز للعلوم والتقنية &copy; 2020</p> '+
            '</div> '+

        '</div> '+
    '</div> '+
'</div>'});

  
  
  Vue.component('myheader',{
	  template: '<div id="header">'+

	  '<nav class="navbar navbar-expand-lg navbar-light bg-light static-top">'+

	      '<div class="container">'+
	       ' <a class="navbar-brand" href="hrs"> الموارد البشرية</a>'+
	       ' <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#home-navbarResponsive" aria-controls="home-navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">'+
	     ' <span class="navbar-toggler-icon"></span>'+
	    '</button>'+
	        '<div class="collapse navbar-collapse" id="home-navbarResponsive">'+
	        '  <ul class="navbar-nav ml-auto">'+
	               	
	          '    <li class="nav-item">'+
	            '  <a class="nav-link" href="staff_home">الموظفون</a>'+
	          '  </li>'+
	             ' <li class="nav-item">'+
	            '  <a class="nav-link" href="overtime_home">خارج الدوام</a>'+
	            '</li>'+
	             '  <li class="nav-item">'+
	            '  <a class="nav-link" href="leave_home">الإجازات</a>'+
	           ' </li>'+

	            '<li class="nav-item">'+
	             ' <a class="nav-link" href="job_home">الوظائف</a>'+
	           ' </li>'+
	            ' <li class="nav-item">'+
	            '  <a class="nav-link" href="grade_home">المراتب</a>'+
	           ' </li>'+
	           ' <li class="nav-item">'+
	            '  <a class="nav-link" href="department_home">الإدارات</a>'+
	           ' </li>'+
	            '  <li class="nav-item">'+
	            '  <a class="nav-link" href="pay_home">Payroll</a>'+
	           ' </li>'+


	             
	         ' </ul>'+
	       ' </div>'+
	     ' </div>'+
	   ' </nav>'+
	 
	         ' </div>'+
	           
		  '</div>'});
  
  Vue.component('myjs',{
	  template: '<div id="js"></div>'
  });
  
  Vue.component('mycontent',{
	  template: '<div id="content"></div>'
  });

  Vue.component('mypagination',{
	  template: '<div id="pagination">'+

	  '<div class="row">'+
	     ' <div class="col-md-3"></div>'+
	   '<div class="col-md-8">'+
	       
	         ' <div class="home-pagination">'+
	          '    <span class="page-links">'+
	                  
	                '      <a href="{ request.path }?page={ page_obj.previous_page_number }">السابق</a> '+
	                  
	                 ' <span class="page-current">'+
	                     
	                     '     { page_obj.number } من صفحتين'+
	                    ' <br>'+
	                      '    { page_obj.number } من { page_obj.paginator.num_pages } صفحات'+
	                     ' <br>'+
	                      '    { page_obj.number } من { page_obj.paginator.num_pages } صفحة'+
	                      

	                 ' </span>'+
	                  
	                    '  <a href="{ request.path }?page={ page_obj.next_page_number }">التالي</a>'+
	                 
	            '  </span>'+
	         ' </div>'+
	          
	      
	  ' </div>'+
	  '</div>'+
	  '</div>'});
  
  
  var app = new Vue({
    el:'#app'
  })