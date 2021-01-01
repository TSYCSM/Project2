// Anonymous "self-invoking" function
(function() {
    var startingTime = new Date().getTime();
    // Load the script
    var script = document.createElement("SCRIPT");
    script.src = 'https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js';
    script.type = 'text/javascript';
    document.getElementsByTagName("head")[0].appendChild(script);

    // Poll for jQuery to come into existance
    var checkReady = function(callback) {
        if (window.jQuery) {
            callback(jQuery);
        }
        else {
            window.setTimeout(function() { checkReady(callback); }, 20);
        }
    };

    // Start polling...
    checkReady(function($) {
        $(function() {
            var endingTime = new Date().getTime();
            var tookTime = endingTime - startingTime;
        });
    });
})();

function hideElement(element){
	$(document).mouseup(function(e){
    var container = $(element);
 
    // If the target of the click isn't the container
    if(!container.is(e.target) && container.has(e.target).length === 0){
        container.hide();
    	}
	});
}

function showMypage(){
	$('.mypagebar').toggle();
	hideElement(".mypagebar");
	
}

function showReports(){
	$('.reportsbar').toggle();
	hideElement(".reportsbar");
}

function showItem(){
	$('.itembar').toggle();
	hideElement(".itembar");
}


function openNav() {
	document.getElementById("mySidenav").style.width = "250px";	
}

function closeNav(){
	document.getElementById("mySidenav").style.width = "0px";			
}



function loadNav() {
	var dropdown = document.getElementsByClassName("dropdown-btn");
	var i;

	for (i = 0; i < dropdown.length; i++) {
		dropdown[i].addEventListener("click", function() {
			this.classList.toggle("active");
			var dropdownContent = this.nextElementSibling;
			if (dropdownContent.style.display === "block") {
				dropdownContent.style.display = "none";
			} else {
				dropdownContent.style.display = "block";
			}
		});
	}
}