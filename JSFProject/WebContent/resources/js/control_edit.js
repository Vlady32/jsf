$(document).ready(function(){
	
	$("tr").mouseover(function() {
		$(".tools",this).removeClass('hidden');
	});
	
	$("tr").mouseout(function() {
		$(".tools",this).addClass('hidden');
	});
	
})

