 const signupForm = document.getElementById('signup-form');
     const nidInput  = signupForm.querySelector('input[name=nid]');
    //listen for the submit event
     signupForm.addEventListener('submit', processSignupForm);
     function processSignupForm(e) {
     e.preventDefault();
	
     const nid =  nidInput.value;

     console.log({ nid });
    }

   

    // vue ==========================================
    var app = new Vue({
      el: '#signup-form',
      data: {
    	nid: '',
        errors: {
          name: false,
          email: false
        }
      },
      methods: {
        processForm: function() {
          console.log({ nid: this.nid });
        },
      submitProduct: function () {
    	  axios.post('saveLeaveReturn', {
    		    nid: this.nid
            }).then(response => {
                window.location.href = 'hrs';
            })
        }
      }
    });