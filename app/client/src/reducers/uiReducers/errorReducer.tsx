import { createReducer } from "utils/ReducerUtils";
import {
  ReduxAction,
  ReduxActionTypes,
  ReduxActionErrorPayload,
} from "@bizBrainz/constants/ReduxActionConstants";
import { ERROR_CODES } from "@bizBrainz/constants/ApiConstants";
import _ from "lodash";

const initialState: ErrorReduxState = {
  safeCrash: false,
  safeCrashCode: undefined,
  currentError: { sourceAction: "", message: "" },
};

const errorReducer = createReducer(initialState, {
  [ReduxActionTypes.SAFE_CRASH_BIZBRAINZ]: (
    state: ErrorReduxState,
    action: ReduxAction<ReduxActionErrorPayload>,
  ) => ({
    ...state,
    safeCrash: true,
    safeCrashCode: _.get(action, "payload.code", 502), // when the server is not responding
  }),
  [ReduxActionTypes.REPORT_ERROR]: (
    state: ErrorReduxState,
    action: ReduxAction<ReduxActionErrorPayload>,
  ) => {
    return {
      ...state,
      currentError: {
        sourceAction: action.payload.source,
        message: action.payload.message,
      },
    };
  },
  [ReduxActionTypes.FLUSH_ERRORS]: () => {
    return initialState;
  },
});

export interface ErrorReduxState {
  safeCrash: boolean;
  safeCrashCode?: ERROR_CODES;
  currentError: {
    sourceAction?: string;
    message?: string;
  };
}

export default errorReducer;
