import { ReduxActionTypes } from "@bizBrainz/constants/ReduxActionConstants";

export const resetReleasesCount = () => ({
  type: ReduxActionTypes.RESET_UNREAD_RELEASES_COUNT,
});
