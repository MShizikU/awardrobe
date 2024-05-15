FROM node:slim
WORKDIR /app
COPY package*.json ./app
RUN npm install react-scripts@3.4.1 && npm install
COPY . .
EXPOSE 3000
CMD ["npm", "start"]