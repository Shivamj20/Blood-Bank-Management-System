document.addEventListener("DOMContentLoaded",async ()=>{
	let totalDonors = document.getElementById("total_donors");
	let bloodRequests = document.getElementById("blood_requests");
	let stockAvailable = document.getElementById("stock_available");

	try{
		let resp = await fetch("http://localhost:2025/BloodSync/AdminDashboardServlet");
			let data = await resp.json();
			if(data.status ==='failed')
				throw new Error("Exception occured : "+data.message);
			totalDonors.innerText=data.totalDonors;
			bloodRequests.innerText=data.totalRequests;
			stockAvailable.innerText=data.totalStock;
	}catch(error){
		console.log(error);
		error.printStackTrace();
	}
});
