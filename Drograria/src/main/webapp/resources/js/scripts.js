function handleLoginRequest(xhr, status, args) {
	if (args.validationFailed || !args.loggedIn) {
		PF('dlg').jq.effect("shake", { times: 5 }, 100);
	} else {
		PF('dlg').hide();
		$('#loginLink').fadeOut();
	}
}

function verificarCamposRequeridos(xhr, status, args, dialogName, dataTableName) {
	if (args.validationFailed) {
		PF(dialogName).jq.effect("shake", { times: 5 }, 100);
	} else {
		PF(dialogName).hide();
		PF(dataTableName).clearFilters();
	}
}