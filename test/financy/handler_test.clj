(ns financy.handler-test
  (:refer-clojure :exclude [any?])
  (:require [midje.sweet :refer [fact facts]]
            [ring.mock.request :as mock]
            [financy.handler :refer :all]
            [midje.parsing.arrow-symbols :refer [=>]]))

(facts "GET '/'"
       (let [response (app (mock/request :get "/"))]
         (fact "status code must be 200"
               (:status response) => 200)

         (fact "must be returns 'Hello World'"
               (:body response) => "Hello World")))

(facts "not found"
       (let [response (app (mock/request :get "/any-path"))]
         (fact "/any-path"
               (:status response) => 404)
         (fact "body must be returns 'Not Found'"
               (let [response (app (mock/request :get "/invalid"))]
                 (:body	response) => "Not Found"))))
