const prodplugins = []
if(process.env.NODE_Env === 'production'){
  prodplugins.push('transform-remove-console')
}


module.exports = {
  'presets': [
    '@vue/cli-plugin-babel/preset'
  ],
  'plugins':[
      ...prodplugins
    ]
}
