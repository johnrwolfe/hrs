//plain javscript ==================================
     //grab the things we need
     const signupForm = document.getElementById('signup-form');
     const startInput  = signupForm.querySelector('input[name=start]');
     const endInput  = signupForm.querySelector('input[name=end]');
     const nidInput  = signupForm.querySelector('input[name=nid]');
     const leaveInput  = signupForm.querySelector('input[name=leave]');
    //listen for the submit event
     signupForm.addEventListener('submit', processSignupForm);
     function processSignupForm(e) {
     e.preventDefault();
	
     const start =  startInput.value;
     const end =  endInput.value;
     const nid =  nidInput.value;
     const leave =  leaveInput.value;

     console.log({ start, end, nid, leave});
     
    }

   

    // vue ==========================================
    var app = new Vue({
      el: '#signup-form',
      data: {
    	start: '',
    	end: '',
    	nid: '',
    	leave: '',
        errors: {
          name: false,
          email: false
        }
      },
      methods: {
        processForm: function() {
          console.log({ start: this.start, end: this.end, nid: this.nid, leave: this.leave});
          
        },
      submitProduct: function () {
    	  axios.post('saveLeaveRequest', {
    		    start: this.start,
    		    end: this.end,
    		    nid: this.nid,
    		    leave: this.leave
            }).then(response => {
                window.location.href = 'hrs';
            })
        }
      }
    });
