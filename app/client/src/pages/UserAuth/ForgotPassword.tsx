import React, { useEffect } from "react";
import { connect, useDispatch } from "react-redux";
import { withRouter, RouteComponentProps } from "react-router-dom";
import {
  change,
  reduxForm,
  InjectedFormProps,
  formValueSelector,
} from "redux-form";
import StyledForm from "components/editorComponents/Form";
import {
  AuthCardHeader,
  FormActions,
  BlackAuthCardNavLink,
  FormMessagesContainer,
} from "./StyledComponents";
import { withTheme } from "styled-components";
import { Theme } from "constants/DefaultTheme";
import {
  FORGOT_PASSWORD_PAGE_EMAIL_INPUT_LABEL,
  FORGOT_PASSWORD_PAGE_EMAIL_INPUT_PLACEHOLDER,
  FORGOT_PASSWORD_PAGE_SUBMIT_BUTTON_TEXT,
  FORGOT_PASSWORD_PAGE_TITLE,
  FORM_VALIDATION_EMPTY_EMAIL,
  FORM_VALIDATION_INVALID_EMAIL,
  FORGOT_PASSWORD_SUCCESS_TEXT,
  FORGOT_PASSWORD_PAGE_LOGIN_LINK,
  createMessage,
} from "@bizBrainz/constants/messages";
import { AUTH_LOGIN_URL } from "constants/routes";
import FormMessage from "components/ads/formFields/FormMessage";
import { FORGOT_PASSWORD_FORM_NAME } from "@bizBrainz/constants/forms";
import FormGroup from "components/ads/formFields/FormGroup";
import FormTextField from "components/utils/ReduxFormTextField";
import { Button, Size } from "design-system";
import { Icon } from "@blueprintjs/core";
import { isEmail, isEmptyString } from "utils/formhelpers";
import {
  ForgotPasswordFormValues,
  forgotPasswordSubmitHandler,
} from "./helpers";
import { getBizbrainzConfigs } from "@bizBrainz/configs";

const { mailEnabled } = getBizbrainzConfigs();

const validate = (values: ForgotPasswordFormValues) => {
  const errors: ForgotPasswordFormValues = {};
  if (!values.email || isEmptyString(values.email)) {
    errors.email = createMessage(FORM_VALIDATION_EMPTY_EMAIL);
  } else if (!isEmail(values.email)) {
    errors.email = createMessage(FORM_VALIDATION_INVALID_EMAIL);
  }
  return errors;
};

type ForgotPasswordProps = InjectedFormProps<
  ForgotPasswordFormValues,
  { emailValue: string }
> &
  RouteComponentProps<{ email: string }> & { emailValue: string };

export const ForgotPassword = withTheme(
  (props: ForgotPasswordProps & { theme: Theme }) => {
    const {
      error,
      handleSubmit,
      submitFailed,
      submitSucceeded,
      submitting,
    } = props;
    const dispatch = useDispatch();

    useEffect(() => {
      if (submitSucceeded) {
        props.reset();
        dispatch(change(FORGOT_PASSWORD_FORM_NAME, "email", ""));
      }
    }, [props.emailValue]);

    return (
      <>
        <AuthCardHeader>
          <h1>{createMessage(FORGOT_PASSWORD_PAGE_TITLE)}</h1>
        </AuthCardHeader>
        <div style={{ display: "flex", justifyContent: "center" }}>
          <BlackAuthCardNavLink to={AUTH_LOGIN_URL}>
            <Icon
              icon="arrow-left"
              style={{ marginRight: props.theme.spaces[3] }}
            />
            {createMessage(FORGOT_PASSWORD_PAGE_LOGIN_LINK)}
          </BlackAuthCardNavLink>
        </div>
        <FormMessagesContainer>
          {submitSucceeded && (
            <FormMessage
              intent="lightSuccess"
              message={createMessage(
                FORGOT_PASSWORD_SUCCESS_TEXT,
                props.emailValue,
              )}
            />
          )}
          {!mailEnabled && (
            <FormMessage
              actions={[
                {
                  url: "https://docs.bizBrainz.com/v/v1.2.1/setup/docker/email",
                  text: "Configure Email service",
                  intent: "primary",
                },
              ]}
              intent="warning"
              message={
                "You haven’t setup any email service yet. Please configure your email service to receive a reset link"
              }
            />
          )}
          {submitFailed && error && (
            <FormMessage intent="warning" message={error} />
          )}
        </FormMessagesContainer>
        <StyledForm onSubmit={handleSubmit(forgotPasswordSubmitHandler)}>
          <FormGroup
            intent={error ? "danger" : "none"}
            label={createMessage(FORGOT_PASSWORD_PAGE_EMAIL_INPUT_LABEL)}
          >
            <FormTextField
              disabled={submitting}
              name="email"
              placeholder={createMessage(
                FORGOT_PASSWORD_PAGE_EMAIL_INPUT_PLACEHOLDER,
              )}
            />
          </FormGroup>
          <FormActions>
            <Button
              disabled={!isEmail(props.emailValue)}
              fill
              isLoading={submitting}
              size={Size.large}
              tag="button"
              text={createMessage(FORGOT_PASSWORD_PAGE_SUBMIT_BUTTON_TEXT)}
              type="submit"
            />
          </FormActions>
        </StyledForm>
      </>
    );
  },
);

const selector = formValueSelector(FORGOT_PASSWORD_FORM_NAME);

export default connect((state, props: ForgotPasswordProps) => {
  const queryParams = new URLSearchParams(props.location.search);
  return {
    initialValues: {
      email: queryParams.get("email") || "",
    },
    emailValue: selector(state, "email"),
  };
})(
  reduxForm<ForgotPasswordFormValues, { emailValue: string }>({
    validate,
    form: FORGOT_PASSWORD_FORM_NAME,
    touchOnBlur: true,
  })(withRouter(ForgotPassword)),
);
