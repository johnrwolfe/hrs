	 //plain javscript ==================================
     //grab the things we need
     const signupForm = document.getElementById('signup-form');
     const nidInput = signupForm.querySelector('input[name=nid]');

    //listen for the submit event
     signupForm.addEventListener('submit', processSignupForm);
     function processSignupForm(e) {
     e.preventDefault();

     const nid = nidInput.value;

     console.log({ nid});
     
    }

   

    // vue ==========================================
    var form = new Vue({
      el: '#signup-form',
      data: {
    	nid:[],
        options: null,
        errors: {
          name: false,
          email: false
        }
      },
      mounted () {
  	    axios
  	      .get('listOfRequestedLeaves')
  	      .then(response => (this.options = response.data))
  	  },
      methods: {
        processForm: function() {
          console.log({ nid: this.nid});
          
        },
      submitProduct: function () {
    	  axios.post('approvedLeave', {
    		  nid: this.nid
            }).then(window.location.reload(true));
        },
        gotoHome: function(){
        	window.location.href = 'hrs';
        },
        submitReject:function () {
    	  axios.post('rejectLeave', {
    		  nid: this.nid
            }).then(window.location.reload(true));
        }
      }
    });

    
