(ns tangoclango.app-servlet
  (:gen-class :extends javax.servlet.http.HttpServlet)
  (:require [compojure.core :refer :all]
            [compojure.handler :refer [site]]
            [compojure.route :refer [resources not-found]]
            [noir.response :refer [redirect]]
            [noir.session :as session]
            ;[routegen.core :refer [page-routes service-routes rest-routes]]
            [tangoclango.pages]
            [tangoclango.services]))

(defn login [username password]
  (println "login" username)
  (session/put! :username username)
  (session/put! :password password)
  username)

(def app-routes
  (apply routes
         (concat
          ;(page-routes 'tangoclango.pages)
          ;(service-routes 'tangoclango.services)
          ;(rest-routes 'tangoclango.services)
          [(POST "/login" [username password] (login username password))
           (GET "/" [] (tangoclango.pages/home))
           (GET "" [] (redirect "/"))
           (resources "/")
           (not-found "Not Found")])))

(def app-handler
  (session/wrap-noir-session (site app-routes)))


