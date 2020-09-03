//plain javscript ==================================
     //grab the things we need
     const signupForm = document.getElementById('signup-form');

    //listen for the submit event
     signupForm.addEventListener('submit', processSignupForm);
     function processSignupForm(e) {
     e.preventDefault();

    }

   

    // vue ==========================================
    var app = new Vue({
      el: '#signup-form',
      data: {
    	allEmployees:null,
        errors: {
          name: false,
          email: false
        }
      },
      mounted () {
    	    axios
    	      .get('listEmployees')
    	      .then(response => (this.allEmployees = response.data))
    	  },
      methods: {
        processForm: function(event) {
        	targetId = event.currentTarget.id;
          console.log({ value: targetId});
        },
      seeID: function(event) {
      	targetId = event.currentTarget.id;
    	  console.log( {targetId: targetId} );
    	  axios.post('getEmployeeID', {
    		  employeeID: targetId
            }).then(response => {
                window.location.href = 'staff_detail';
            })
        }
      }
    });