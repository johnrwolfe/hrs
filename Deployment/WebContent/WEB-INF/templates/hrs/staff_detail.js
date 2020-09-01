//plain javscript ==================================
     //grab the things we need
 

    // vue ==========================================
    var app = new Vue({
      el: '#action-home',
      data: {
    	  employee:null,
        errors: {
          name: false,
          email: false
        }
      },
      mounted () {
    	    axios
    	      .get('employeeInfo')
    	      .then(response => (this.employee = response.data))
    	  }
    });