(ns server-helper
  (:require [ring.adapter.jetty :refer [run-jetty]]
                        [financy.handler :refer [app]]
                        [clj-http.client :as http]))

(def state-server (atom nil))

(def default-port 3001)

(defn start-server
  [port]
  (swap! state-server
         (fn [_] (run-jetty app {:port port :join? false}))))

(defn url-for
  [path]
  (str "http://localhost:" default-port path))

(def request-to
  (comp http/get url-for))

(defn content
  [path]
  (:body (request-to path)))

(defn stop-server
  []
  (.stop @state-server))
