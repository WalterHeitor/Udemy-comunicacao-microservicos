FROM node:14
WORKDIR .
COPY packege*.json/ ./
RUN yarn
COPY . .
EXPOSE 8080
CMD [ "node", "app.js" ]