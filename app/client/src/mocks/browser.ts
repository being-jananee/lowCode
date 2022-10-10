import { setupWorker } from "msw";
import { handlers } from "@bizBrainz/mocks/handlers";

export const worker = setupWorker(...handlers);
