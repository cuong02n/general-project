from tensorflow.keras.models import load_model

from ocr_helper import *

if __name__ == "__main__":
    # Get the model
    loaded_model = load_model('model.keras', custom_objects={'CTCLayer': CTCLayer})

    prediction_model = keras.models.Model(
        loaded_model.get_layer(name="image").output, loaded_model.get_layer(name="dense2").output
    )

    image_path = '00056.png'
    image_test = encode_single_sample(image_path)
    image_test = np.expand_dims(image_test, axis=0)

    preds = prediction_model.predict(image_test)
    pred_texts = decode_batch_predictions(preds)
    print(pred_texts)
