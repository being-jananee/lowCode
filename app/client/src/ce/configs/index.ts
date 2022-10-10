import { BizbrainzUIConfigs } from "./types";
import { Integrations } from "@sentry/tracing";
import * as Sentry from "@sentry/react";
import { createBrowserHistory } from "history";
const history = createBrowserHistory();

export interface INJECTED_CONFIGS {
  sentry: {
    dsn: string;
    release: string;
    environment: string;
  };
  smartLook: {
    id: string;
  };
  enableGoogleOAuth: boolean;
  enableGithubOAuth: boolean;
  disableLoginForm: boolean;
  disableSignup: boolean;
  disableTelemetry: boolean;
  enableRapidAPI: boolean;
  segment: {
    apiKey: string;
    ceKey: string;
  };
  fusioncharts: {
    licenseKey: string;
  };
  enableMixpanel: boolean;
  google: string;
  enableTNCPP: boolean;
  cloudHosting: boolean;
  algolia: {
    apiId: string;
    apiKey: string;
    indexName: string;
    snippetIndex: string;
  };
  logLevel: "debug" | "error";
  appVersion: {
    id: string;
    releaseDate: string;
    edition: string;
  };
  intercomAppID: string;
  mailEnabled: boolean;
  cloudServicesBaseUrl: string;
  googleRecaptchaSiteKey: string;
  supportEmail: string;
  hideWatermark: boolean;
  disableIframeWidgetSandbox: boolean;
}

const capitalizeText = (text: string) => {
  const rest = text.slice(1);
  const first = text[0].toUpperCase();
  return `${first}${rest}`;
};

export const getConfigsFromEnvVars = (): INJECTED_CONFIGS => {
  return {
    sentry: {
      dsn: process.env.REACT_APP_SENTRY_DSN || "",
      release: process.env.REACT_APP_SENTRY_RELEASE || "",
      environment:
        process.env.REACT_APP_SENTRY_ENVIRONMENT ||
        capitalizeText(process.env.NODE_ENV),
    },
    smartLook: {
      id: process.env.REACT_APP_SMART_LOOK_ID || "",
    },
    enableGoogleOAuth: process.env.REACT_APP_OAUTH2_GOOGLE_CLIENT_ID
      ? process.env.REACT_APP_OAUTH2_GOOGLE_CLIENT_ID.length > 0
      : false,
    enableGithubOAuth: process.env.REACT_APP_OAUTH2_GITHUB_CLIENT_ID
      ? process.env.REACT_APP_OAUTH2_GITHUB_CLIENT_ID.length > 0
      : false,
    disableLoginForm: process.env.BIZBRAINZ_FORM_LOGIN_DISABLED
      ? process.env.BIZBRAINZ_FORM_LOGIN_DISABLED.length > 0
      : false,
    disableSignup: process.env.BIZBRAINZ_SIGNUP_DISABLED
      ? process.env.BIZBRAINZ_SIGNUP_DISABLED.length > 0
      : false,
    disableTelemetry: process.env.BIZBRAINZ_DISABLE_TELEMETRY
      ? process.env.BIZBRAINZ_DISABLE_TELEMETRY.length > 0
      : false,
    segment: {
      apiKey: process.env.REACT_APP_SEGMENT_KEY || "",
      ceKey: process.env.REACT_APP_SEGMENT_CE_KEY || "",
    },
    fusioncharts: {
      licenseKey: process.env.REACT_APP_FUSIONCHARTS_LICENSE_KEY || "",
    },
    enableMixpanel: process.env.REACT_APP_SEGMENT_KEY
      ? process.env.REACT_APP_SEGMENT_KEY.length > 0
      : false,
    algolia: {
      apiId: process.env.REACT_APP_ALGOLIA_API_ID || "",
      apiKey: process.env.REACT_APP_ALGOLIA_API_KEY || "",
      indexName: process.env.REACT_APP_ALGOLIA_SEARCH_INDEX_NAME || "",
      snippetIndex: process.env.REACT_APP_ALGOLIA_SNIPPET_INDEX_NAME || "",
    },
    logLevel:
      (process.env.REACT_APP_CLIENT_LOG_LEVEL as
        | "debug"
        | "error"
        | undefined) || "error",
    google: process.env.REACT_APP_GOOGLE_MAPS_API_KEY || "",
    enableTNCPP: process.env.REACT_APP_TNC_PP
      ? process.env.REACT_APP_TNC_PP.length > 0
      : false,
    enableRapidAPI: process.env.REACT_APP_MARKETPLACE_URL
      ? process.env.REACT_APP_MARKETPLACE_URL.length > 0
      : false,
    cloudHosting: process.env.REACT_APP_CLOUD_HOSTING
      ? process.env.REACT_APP_CLOUD_HOSTING.length > 0
      : false,
    appVersion: {
      id: process.env.REACT_APP_VERSION_ID || "",
      releaseDate: process.env.REACT_APP_VERSION_RELEASE_DATE || "",
      edition: process.env.REACT_APP_VERSION_EDITION || "",
    },
    intercomAppID: process.env.REACT_APP_INTERCOM_APP_ID || "",
    mailEnabled: process.env.REACT_APP_MAIL_ENABLED
      ? process.env.REACT_APP_MAIL_ENABLED.length > 0
      : false,
    cloudServicesBaseUrl: process.env.REACT_APP_CLOUD_SERVICES_BASE_URL || "",
    googleRecaptchaSiteKey:
      process.env.REACT_APP_GOOGLE_RECAPTCHA_SITE_KEY || "",
    supportEmail: process.env.BIZBRAINZ_SUPPORT_EMAIL || "support@bizBrainz.com",
    hideWatermark: process.env.BIZBRAINZ_HIDE_WATERMARK
      ? process.env.BIZBRAINZ_HIDE_WATERMARK.length > 0
      : false,
    disableIframeWidgetSandbox: process.env
      .BIZBRAINZ_DISABLE_IFRAME_WIDGET_SANDBOX
      ? process.env.BIZBRAINZ_DISABLE_IFRAME_WIDGET_SANDBOX.length > 0
      : false,
  };
};

const getConfig = (fromENV: string, fromWindow = "") => {
  if (fromWindow.length > 0) return { enabled: true, value: fromWindow };
  else if (fromENV.length > 0) return { enabled: true, value: fromENV };
  return { enabled: false, value: "" };
};

// TODO(Abhinav): See if this is called so many times, that we may need some form of memoization.
export const getBizbrainzConfigs = (): BizbrainzUIConfigs => {
  const { BIZBRAINZ_FEATURE_CONFIGS } = window;
  const ENV_CONFIG = getConfigsFromEnvVars();

  // const sentry = getConfig(ENV_CONFIG.sentry, BIZBRAINZ_FEATURE_CONFIGS.sentry);
  const sentryDSN = getConfig(
    ENV_CONFIG.sentry.dsn,
    BIZBRAINZ_FEATURE_CONFIGS.sentry.dsn,
  );
  const sentryRelease = getConfig(
    ENV_CONFIG.sentry.release,
    BIZBRAINZ_FEATURE_CONFIGS.sentry.release,
  );
  const sentryENV = getConfig(
    ENV_CONFIG.sentry.environment,
    BIZBRAINZ_FEATURE_CONFIGS.sentry.environment,
  );
  const segment = getConfig(
    ENV_CONFIG.segment.apiKey,
    BIZBRAINZ_FEATURE_CONFIGS.segment.apiKey,
  );
  const fusioncharts = getConfig(
    ENV_CONFIG.fusioncharts.licenseKey,
    BIZBRAINZ_FEATURE_CONFIGS.fusioncharts.licenseKey,
  );
  const google = getConfig(ENV_CONFIG.google, BIZBRAINZ_FEATURE_CONFIGS.google);

  const googleRecaptchaSiteKey = getConfig(
    ENV_CONFIG.googleRecaptchaSiteKey,
    BIZBRAINZ_FEATURE_CONFIGS.googleRecaptchaSiteKey,
  );

  // As the following shows, the config variables can be set using a combination
  // of env variables and injected configs
  const smartLook = getConfig(
    ENV_CONFIG.smartLook.id,
    BIZBRAINZ_FEATURE_CONFIGS.smartLook.id,
  );

  const algoliaAPIID = getConfig(
    ENV_CONFIG.algolia.apiId,
    BIZBRAINZ_FEATURE_CONFIGS.algolia.apiId,
  );
  const algoliaAPIKey = getConfig(
    ENV_CONFIG.algolia.apiKey,
    BIZBRAINZ_FEATURE_CONFIGS.algolia.apiKey,
  );
  const algoliaIndex = getConfig(
    ENV_CONFIG.algolia.indexName,
    BIZBRAINZ_FEATURE_CONFIGS.algolia.indexName,
  );
  const algoliaSnippetIndex = getConfig(
    ENV_CONFIG.algolia.indexName,
    BIZBRAINZ_FEATURE_CONFIGS.algolia.snippetIndex,
  );

  const segmentCEKey = getConfig(
    ENV_CONFIG.segment.ceKey,
    BIZBRAINZ_FEATURE_CONFIGS.segment.ceKey,
  );

  // We enable segment tracking if either the Cloud API key is set or the self-hosted CE key is set
  segment.enabled = segment.enabled || segmentCEKey.enabled;

  return {
    sentry: {
      enabled: sentryDSN.enabled && sentryRelease.enabled && sentryENV.enabled,
      dsn: sentryDSN.value,
      release: sentryRelease.value,
      environment: sentryENV.value,
      normalizeDepth: 3,
      integrations: [
        new Integrations.BrowserTracing({
          // Can also use reactRouterV4Instrumentation
          routingInstrumentation: Sentry.reactRouterV5Instrumentation(history),
        }),
      ],
      tracesSampleRate: 0.1,
    },
    smartLook: {
      enabled: smartLook.enabled,
      id: smartLook.value,
    },
    segment: {
      enabled: segment.enabled,
      apiKey: segment.value,
      ceKey: segmentCEKey.value,
    },
    fusioncharts: {
      enabled: fusioncharts.enabled,
      licenseKey: fusioncharts.value,
    },
    algolia: {
      enabled: true,
      apiId: algoliaAPIID.value || "AZ2Z9CJSJ0",
      apiKey: algoliaAPIKey.value || "d113611dccb80ac14aaa72a6e3ac6d10",
      indexName: algoliaIndex.value || "test_bizBrainz",
      snippetIndex: algoliaSnippetIndex.value || "snippet",
    },
    google: {
      enabled: google.enabled,
      apiKey: google.value,
    },
    googleRecaptchaSiteKey: {
      enabled: googleRecaptchaSiteKey.enabled,
      apiKey: googleRecaptchaSiteKey.value,
    },
    enableRapidAPI:
      ENV_CONFIG.enableRapidAPI || BIZBRAINZ_FEATURE_CONFIGS.enableRapidAPI,
    enableGithubOAuth:
      ENV_CONFIG.enableGithubOAuth ||
      BIZBRAINZ_FEATURE_CONFIGS.enableGithubOAuth,
    disableLoginForm:
      ENV_CONFIG.disableLoginForm || BIZBRAINZ_FEATURE_CONFIGS.disableLoginForm,
    disableSignup:
      ENV_CONFIG.disableSignup || BIZBRAINZ_FEATURE_CONFIGS.disableSignup,
    disableTelemetry:
      ENV_CONFIG.disableTelemetry || BIZBRAINZ_FEATURE_CONFIGS.disableTelemetry,
    enableGoogleOAuth:
      ENV_CONFIG.enableGoogleOAuth ||
      BIZBRAINZ_FEATURE_CONFIGS.enableGoogleOAuth,
    enableMixpanel:
      ENV_CONFIG.enableMixpanel || BIZBRAINZ_FEATURE_CONFIGS.enableMixpanel,
    cloudHosting:
      ENV_CONFIG.cloudHosting || BIZBRAINZ_FEATURE_CONFIGS.cloudHosting,
    logLevel: ENV_CONFIG.logLevel || BIZBRAINZ_FEATURE_CONFIGS.logLevel,
    enableTNCPP: ENV_CONFIG.enableTNCPP || BIZBRAINZ_FEATURE_CONFIGS.enableTNCPP,
    appVersion: ENV_CONFIG.appVersion || BIZBRAINZ_FEATURE_CONFIGS.appVersion,
    intercomAppID:
      ENV_CONFIG.intercomAppID || BIZBRAINZ_FEATURE_CONFIGS.intercomAppID,
    mailEnabled: ENV_CONFIG.mailEnabled || BIZBRAINZ_FEATURE_CONFIGS.mailEnabled,
    cloudServicesBaseUrl:
      ENV_CONFIG.cloudServicesBaseUrl ||
      BIZBRAINZ_FEATURE_CONFIGS.cloudServicesBaseUrl,
    bizBrainzSupportEmail: ENV_CONFIG.supportEmail,
    hideWatermark:
      ENV_CONFIG.hideWatermark || BIZBRAINZ_FEATURE_CONFIGS.hideWatermark,
    disableIframeWidgetSandbox:
      ENV_CONFIG.disableIframeWidgetSandbox ||
      BIZBRAINZ_FEATURE_CONFIGS.disableIframeWidgetSandbox,
  };
};
