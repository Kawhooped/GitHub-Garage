import { proxyAI } from "../../../_shared/ai-gateway.js";
export function onRequest(context) {
  return proxyAI(context, "models");
}
