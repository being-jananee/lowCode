import { AppState } from "@bizBrainz/reducers";

export const getIsCanvasInitialized = (state: AppState) => {
  return state.ui.mainCanvas.initialized;
};
