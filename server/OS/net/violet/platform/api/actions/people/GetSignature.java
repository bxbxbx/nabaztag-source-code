package net.violet.platform.api.actions.people;

import java.util.List;

import net.violet.platform.api.actions.ActionParam;
import net.violet.platform.api.exceptions.ForbiddenException;
import net.violet.platform.api.exceptions.InvalidParameterException;
import net.violet.platform.api.exceptions.InvalidSessionException;
import net.violet.platform.api.exceptions.NoSuchPersonException;
import net.violet.platform.api.maps.SignatureInformationMap;
import net.violet.platform.datamodel.Application;
import net.violet.platform.datamodel.Application.ApplicationClass;
import net.violet.platform.dataobjects.UserData;
import net.violet.platform.util.Constantes;

public class GetSignature extends AbstractUserAction {

	/**
	 * @throws NoSuchPersonException
	 * @throws InvalidParameterException
	 * @throws InvalidSessionException
	 * @throws ForbiddenException
	 * @see net.violet.platform.api.actions.Action#processRequest(net.violet.platform.api.actions.ActionParam)
	 */
	@Override
	protected Object doProcessRequest(ActionParam inParam) throws InvalidParameterException, NoSuchPersonException {

		final UserData theUser = getRequestedUser(inParam, null);
		// doesSessionBelongToUser(theUser, inParam);

		return new SignatureInformationMap(inParam.getCaller(), theUser);
	}

	public long getExpirationTime() {
		return Constantes.ONE_DAY_IN_S;
	}

	public ActionType getType() {
		return ActionType.GET;
	}

	public boolean isCacheable() {
		return true;
	}

	@Override
	public List<ApplicationClass> getAuthorizedApplicationClasses() {
		return Application.CLASSES_UI;
	}
}
