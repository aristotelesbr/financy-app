(ns financy.handler
  (:require [compojure.core :refer [GET defroutes]]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [cheshire.core :as json]))

(defn json-balance
  []
  {:headers {"Content-Type" "application/json; charset=utf-8"}
   :body (json/generate-string {:balance 0})})

(defroutes app-routes
  (GET "/" [] "Hello World")
  (GET "/balance" [] (json-balance))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
