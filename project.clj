(defproject clojure-getting-started "1.0.0-SNAPSHOT"
  :description "Untangled Cookbook Recipe"
  :url ""
  :license {:name "MIT"
            :url  "https://opensource.org/licenses/MIT"}
  :min-lein-version "2.6.1"

  :dependencies [
                 [compojure "1.4.0"]
                 [ring/ring-jetty-adapter "1.4.0"]
                 [environ "1.0.0"]





                 [com.stuartsierra/component "0.3.2"]
                 [com.google.guava/guava "21.0"]
                 [com.datomic/datomic-free "0.9.5206" :exclusions [joda-time com.google.guava/guava]]
                 [com.taoensso/timbre "4.3.1"]
                 [commons-codec "1.10"]
                 [org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.8.51" :exclusions [com.google.guava/guava]]
                 [org.omcljs/om "1.0.0-alpha41"]
                 [binaryage/devtools "0.5.2"]
                 [com.cemerick/piggieback "0.2.1"]
                 [org.clojure/tools.nrepl "0.2.12"]
                 [juxt/dirwatch "0.2.3"]
                 [navis/untangled-client "0.6.0" :exclusions [cljsjs/react org.omcljs/om]]
                 [navis/untangled-server "0.5.1" :exclusions [com.stuartsierra/component]]
                 [navis/untangled-spec "0.3.6"]
                 [navis/untangled-websockets "0.2.0" :exclusions [com.google.guava/guava]]






                 ]
  :plugins [[environ/environ.lein "0.3.1"]]
  :hooks [environ.leiningen.hooks]
  :uberjar-name "clojure-getting-started-standalone.jar"
  :profiles {:production {:env {:production true}}})



(comment (defproject untangled/websockets "1.0.0"


  :plugins [[lein-cljsbuild "1.1.3"]]

  :source-paths ["src/server"]

  :jvm-opts ["-server" "-Xmx1024m" "-Xms512m" "-XX:-OmitStackTraceInFastThrow"]

  :clean-targets ^{:protect false} ["resources/public/js" "target"]

  :cljsbuild {:builds
              [{:id           "dev"
                :source-paths ["src/client" "dev/client"]
                :figwheel     true
                :compiler     {:main                 cljs.user
                               :asset-path           "js/compiled/dev"
                               :output-to            "resources/public/js/compiled/app.js"
                               :output-dir           "resources/public/js/compiled/dev"
                               :optimizations        :none
                               :parallel-build       false
                               :verbose              false
                               :recompile-dependents true
                               :source-map-timestamp true}}]}

  :repl-options {:init-ns          user
                 :nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}

  :uberjar-name "uberjar.jar"
  
  :profiles {

          :uberjar {:main       app.core
                       :source-paths ["prod/server"]
                       :aot        :all
                       :prep-tasks ["compile"
                                    ["cljsbuild" "once" "production"]]
                       :cljsbuild  {:builds [{:id           "production"
                                              :source-paths ["src/client" "prod/client"]
                                              :jar          true
                                              :compiler     {:main          cljs.user
                                                             :output-to     "resources/public/js/app.min.js"
                                                             :output-dir    "resources/public/js/prod"
                                                             :asset-path    "js/prod"                         
                                                             :optimizations :simple}}]}}

          :dev {
                   :source-paths ["dev/server"]
                   :figwheel {:css-dirs ["resources/public/css"]}
                   :dependencies [
                      [figwheel-sidecar "0.5.9" :exclusions [ring/ring-core joda-time org.clojure/tools.reader]]
                   ]
          }

  }




)
)



