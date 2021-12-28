(ns financy.handler-test
  (:require [midje.sweet :refer [fact facts against-background]]
            [ring.mock.request :as mock]
            [financy.handler :refer [app]]
            [midje.parsing.arrow-symbols :refer [=>]]
            [cheshire.core :as json]))

(facts "GET '/'"
       (let [response (app (mock/request :get "/"))]
         (fact "status code must be 200"
               (:status response) => 200)

         (fact "must be returns 'Hello World'"
               (:body response) => "Hello World")))

(facts "GET '/balance'"
       (against-background (json/generate-string {:balance 0}) => "{\"balance\":0}")

       (let [response (app (mock/request :get "/balance"))]
         (fact "status code must be 200"
               (:status response) => 200)

         (fact "must be returns JSON from response"
               (:body response) => "{\"balance\":0}")))

(facts "not found"
       (let [response (app (mock/request :get "/any-path"))]
         (fact "/any-path"
               (:status response) => 404)

         (fact "body must be returns 'Not Found'"
               (let [response (app (mock/request :get "/invalid"))]
                 (:body	response) => "Not Found"))))
