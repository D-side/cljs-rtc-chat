(defproject chat "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.170"]
                 [reagent "0.5.1"]
                 [re-frame "0.6.0"]
                 [garden "1.3.0"]]

  :min-lein-version "2.5.3"

  :source-paths ["src/clj"]

  :plugins [[lein-cljsbuild "1.1.1"]
            [lein-figwheel "0.5.0-2"]
            [lein-garden "0.2.6"]
            [lein-bower "0.5.1"]]
  
  :bower-dependencies [[rtc "rtc-io/rtc#^3.4.0"]
                       [skyblue "1.0.4"]]
  
  :bower {:scripts {:postinstall "wiredep -s resources/public/index.html"}}

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"
                                    "resources/public/css/compiled"]

  :figwheel {:css-dirs ["resources/public/css"]}

  :garden {:builds [{:id "screen"
                     :source-paths ["src/clj"]
                     :stylesheet chat.css/screen
                     :compiler {:output-to "resources/public/css/compiled/screen.css"
                                :pretty-print? true}}]}

  :cljsbuild {:builds [{:id "dev"
                        :source-paths ["src/cljs"]
                        :figwheel {:on-jsload "chat.core/mount-root"}
                        :compiler {:main chat.core
                                   :output-to "resources/public/js/compiled/app.js"
                                   :output-dir "resources/public/js/compiled/out"
                                   :asset-path "js/compiled/out"
                                   :source-map-timestamp true}}

                       {:id "min"
                        :source-paths ["src/cljs"]
                        :compiler {:main chat.core
                                   :output-to "resources/public/js/compiled/app.js"
                                   :optimizations :simple
                                   :closure-defines {goog.DEBUG false}
                                   :pretty-print false}}]})
