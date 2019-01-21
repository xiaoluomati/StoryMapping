module.exports = {
  baseUrl: process.env.NODE_ENV === 'production'
    ? '//your_url'
    : '/',

  outputDir: 'dist',

  assetsDir: 'static',

  filenameHashing: true,

  // When building in multi-pages mode, the webpack config will contain different plugins
  // (there will be multiple instances of html-webpack-plugin and preload-webpack-plugin).
  // Make sure to run vue inspect if you are trying to modify the options for those plugins.
  // pages: {
  //   index: {
  //     // page 的入口
  //     entry: 'src/index/main.js',
  //     // 模板来源
  //     template: 'public/index.html',
  //     // 在 dist/index.html 的输出
  //     filename: 'index.html',
  //     // 当使用 title 选项时，
  //     // template 中的 title 标签需要是 <title><%= htmlWebpackPlugin.options.title %></title>
  //     title: 'Index Page',
  //     // 在这个页面中包含的块，默认情况下会包含
  //     // 提取出来的通用 chunk 和 vendor chunk。
  //     chunks: ['chunk-vendors', 'chunk-common', 'index']
  //   },
  // },

  // eslint-loader 是否在保存的时候检查
  lintOnSave: true,

  // 是否使用包含运行时编译器的Vue核心的构建
  runtimeCompiler: false,

  // 默认情况下 babel-loader 忽略其中的所有文件 node_modules
  transpileDependencies: [],

  // 生产环境 sourceMap
  productionSourceMap: false,

  // cors 相关 https://jakearchibald.com/2017/es-modules-in-browsers/#always-cors
  // corsUseCredentials: false,
  // webpack 配置，键值对象时会合并配置，为方法时会改写配置
  // https://cli.vuejs.org/guide/webpack.html#simple-configuration
  configureWebpack: (config) => {
  },

  // webpack 链接 API，用于生成和修改 webapck 配置
  // https://github.com/mozilla-neutrino/webpack-chain
  chainWebpack: (config) => {
    // 因为是多页面，所以取消 chunks，每个页面只对应一个单独的 JS / CSS
    config.optimization
      .splitChunks({
        cacheGroups: {}
      });

    // 'src/lib' 目录下为外部库文件，不参与 eslint 检测
    config.module
      .rule('eslint')
      .exclude
      .add('/Users/maybexia/Downloads/FE/community_built-in/src/lib')
      .end()
  },

  // 配置高于chainWebpack中关于 css loader 的配置
  css: {
    // 是否开启支持 foo.module.css 样式
    modules: false,

    // 是否使用 css 分离插件 ExtractTextPlugin，采用独立样式文件载入，不采用 <style> 方式内联至 html 文件中
    extract: true,

    // 是否构建样式地图，false 将提高构建速度
    sourceMap: false,

    // css预设器配置项
    loaderOptions: {
      css: {
        // options here will be passed to css-loader
      },

      postcss: {
        // options here will be passed to postcss-loader
      }
    }
  },

  // All options for webpack-dev-server are supported
  // https://webpack.js.org/configuration/dev-server/
  devServer: {
    open: true,

    host: '127.0.0.1',

    port: 3000,

    https: false,

    hotOnly: false,

    proxy: {
      //这里理解成用‘/api’代替target里面的地址，后面组件中我们调用接口时
      // 直接用api代替 比如我要调用'http://40.00.100.100:3002/user/add'，
      // 直接写‘/api/user/add’即可

      // '/api': {
      //   target: 'http://localhost:8090', //你要跨域的网址  比如  'http://news.baidu.com',
      //   secure: false,  // 如果是https接口，需要配置这个参数
      //   changeOrigin: true,//这个参数是用来回避跨站问题的，配置完之后发请求时会自动修改http header里面的host，但是不会修改别的
      //   pathRewrite: {
      //     '^/api': '/api'//路径的替换规则
      //     //这里的配置是正则表达式，以/api开头的将会被用‘/api’替换掉，假如后台文档的接口是 /api/list/xxx
      //     //前端api接口写：axios.get('/api/list/xxx') ，
      //     // 被处理之后实际访问的是：http://news.baidu.com/api/list/xxx
      //   }
      // },
      '/api': {
        target: 'http://172.19.240.12:9999/',  // 通过本地服务器将你的请求转发到这个地址
        // target: 'http://localhost:9999/',  // 通过本地服务器将你的请求转发到这个地址
        changeOrigin: true,  // 设置这个参数可以避免跨域
        pathRewrite: {
          '/api': '/'
        }
      },
    },

    before: app => {
      }
    },
    // 构建时开启多进程处理 babel 编译
    parallel: require('os').cpus().length > 1,

    // https://github.com/vuejs/vue-cli/tree/dev/packages/%40vue/cli-plugin-pwa
    pwa: {},

    // 第三方插件配置
    pluginOptions: {}
  };
