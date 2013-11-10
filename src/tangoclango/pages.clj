(ns tangoclango.pages
  (:require [hiccup.core :refer :all]
            [hiccup.form :refer :all]
            [hiccup.page :refer :all]
            [hiccup.element :refer :all]))


(defn home []
  (let [title "TangoClango"]
    (html5
     [:head
      ; When run on an intranet, IE defaults to compatibility
      ; which does not work for Google Visualization library
      [:meta {:http-equiv "X-UA-Compatible" :content "IE=edge,chrome=1"}]
      [:title title]
      [:link {:rel "icon"
              :href "img/favicon.ico"
              :type "image/x-icon"}]
      [:link {:rel "shortcut"
              :href "img/favicon.ico"
              :type "image/x-icon"}]
      (include-css "/css/bootstrap.min.css")
      (include-css "/css/tangoclango.css")]

     [:body {:authenticate "loginbox"}
      [:header.navbar
       [:div.navbar-inner
        [:a.brand {:href "#/"} [:strong title]]
        [:ul.nav
         [:li.divider-vertical]
         [:li (link-to "/#/" "Home")]
         [:li.divider-vertical]
         [:li (link-to "/#/Clango" "Clango")]
         [:li.divider-vertical]]
        [:div.login.ng-cloak.pull-right {:ng-show "!username"}
         (link-to "/#/login" "Login")]
        [:div.logout.ng-cloak.pull-right {:ng-show "username"}
         [:span "{{username}}"]
         (submit-button {:ng-click "logout()"} "logout")]]]

      [:div#loginbox.modal.hide.fade {:tabindex -1
                                      :role "dialog"
                                      :aria-labelledby "Login"
                                      :aria-hidden "true"}
       [:div.modal-header
        [:button.close {:type "button"
                        :data-dismiss "modal"
                        :aria-hidden "true"} "x"]]
       [:div.modal-body
        [:form {:ng-controller "LoginCtrl"
                :ng-submit "submit()"
                :novalidate true}
         [:div (label "username" "Username") (text-field {:ng-model "username"} "username")]
         [:div (label "password" "Password") (password-field {:ng-model "password"} "password")]
         (submit-button "Login")]]]
      ;TODO: should have a modal-footer with submit, but then no form?

      [:div#content.ng-view "Loading..."]
      (include-js "/js/jquery-1.8.2.min.js")
      (include-js "https://www.google.com/jsapi")
      (include-js "/js/angular-1.0.1.min.js")
      (include-js "/js/angular-resource-1.0.1.min.js")
      (include-js "/js/http-auth-interceptor.js")
      (include-js "/js/bootstrap.min.js")
      (include-js "/js/charts.js")
      (include-js "/js/controllers.js")
      (include-js "/js/tangoclango.js")])))

(defn clango []
  (html
   [:h1 "RaaS"]))



