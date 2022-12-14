import { APP_MODE } from "entities/App";
import {
  ReduxActionTypes,
  ReduxAction,
} from "@bizBrainz/constants/ReduxActionConstants";

export type InitializeEditorPayload = {
  applicationId?: string;
  pageId?: string;
  branch?: string;
  mode: APP_MODE;
};

export const initEditor = (
  payload: InitializeEditorPayload,
): ReduxAction<InitializeEditorPayload> => ({
  type: ReduxActionTypes.INITIALIZE_EDITOR,
  payload,
});

export type InitAppViewerPayload = {
  branch: string;
  applicationId: string;
  pageId: string;
  mode: APP_MODE;
};

export const initAppViewer = ({
  applicationId,
  branch,
  mode,
  pageId,
}: InitAppViewerPayload) => ({
  type: ReduxActionTypes.INITIALIZE_PAGE_VIEWER,
  payload: {
    branch: branch,
    applicationId,
    pageId,
    mode,
  },
});

export const resetEditorRequest = () => ({
  type: ReduxActionTypes.RESET_EDITOR_REQUEST,
});

export const resetEditorSuccess = () => ({
  type: ReduxActionTypes.RESET_EDITOR_SUCCESS,
});
