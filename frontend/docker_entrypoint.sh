#!/bin/bash

echo "stop nginx"
nginx stop
echo "remove env-config.js"
cd /app
rm env-config.js
ls
cd -
"/bin/sh" ./generate_env-config.sh > /app/env-config.js
echo "*******Generated?******"
ls
########################################
# Create env-config.js file in the public folder 
# of the ngnix server
########################################
echo ""
echo ""
echo "*********************"
echo "Content ./app/env-config.js"
echo "*********************"
cd /app
more ./env-config.js

########################################
# Start ngnix server
# The configuration for the server contains also 
# 'daemon off;'')
# to replace the start command for the
# container image.
# CMD ["nginx", "-g", "daemon off;"]
########################################
echo ""
echo ""
echo "*********************"
echo "Start server"
echo "*********************"
nginx